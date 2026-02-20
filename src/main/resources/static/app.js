const output = document.getElementById("output");
const apiBaseInput = document.getElementById("apiBase");
const baseHint = document.getElementById("baseHint");
const currentPage = document.body.dataset.page;
const PAGE_SIZE = 10;
let allDossiers = [];
let currentListPage = 1;

function guessDefaultBase() {
    return `${window.location.origin}/dossier/api/v1/dossiers`;
}

function getApiBase() {
    if (apiBaseInput) {
        return apiBaseInput.value.trim().replace(/\/+$/, "");
    }
    const fromStorage = localStorage.getItem("dossierApiBase");
    return (fromStorage || guessDefaultBase()).replace(/\/+$/, "");
}

function setOutput(value) {
    if (!output) {
        return;
    }
    output.textContent = typeof value === "string" ? value : JSON.stringify(value, null, 2);
}

async function request(path = "", options = {}) {
    const base = getApiBase();
    if (!base) {
        throw new Error("API Base URL is empty.");
    }
    const response = await fetch(`${base}${path}`, options);
    const text = await response.text();
    let body = text;
    try {
        body = text ? JSON.parse(text) : "";
    } catch (_) {
        // Keep raw string response when not JSON.
    }
    if (!response.ok) {
        throw new Error(`HTTP ${response.status} ${response.statusText}\n${typeof body === "string" ? body : JSON.stringify(body, null, 2)}`);
    }
    return body;
}

function toNullIfEmpty(value) {
    const trimmed = value.trim();
    return trimmed === "" ? null : trimmed;
}

function readPersonDossierFromForm() {
    const form = document.getElementById("personForm");
    const payload = {};
    if (!form) {
        return payload;
    }

    new FormData(form).forEach((value, key) => {
        const normalized = toNullIfEmpty(String(value));
        if (normalized !== null) {
            payload[key] = normalized;
        }
    });
    return payload;
}

function writePersonDossierToForm(data) {
    const form = document.getElementById("personForm");
    if (!form || !data) {
        return;
    }
    Array.from(form.elements).forEach((element) => {
        if (!element.name) {
            return;
        }
        const value = data[element.name];
        element.value = value == null ? "" : String(value);
    });
}

function saveBaseToLocalStorage() {
    const value = getApiBase();
    localStorage.setItem("dossierApiBase", value);
    if (baseHint) {
        baseHint.textContent = `Saved: ${value}`;
    }
}

function loadBaseFromLocalStorage() {
    if (!apiBaseInput) {
        return;
    }
    const fromStorage = localStorage.getItem("dossierApiBase");
    const defaultBase = guessDefaultBase();
    apiBaseInput.value = fromStorage || defaultBase;
    if (baseHint) {
        baseHint.textContent = `Default based on host: ${defaultBase}`;
    }
}

async function withOutput(task) {
    try {
        setOutput("Loading...");
        const result = await task();
        setOutput(result === "" ? "Success (no response body)." : result);
        return result;
    } catch (error) {
        setOutput(error.message || String(error));
        throw error;
    }
}

function bindBaseControls() {
    const saveBtn = document.getElementById("saveBaseBtn");
    if (saveBtn) {
        saveBtn.addEventListener("click", saveBaseToLocalStorage);
    }
}

function renderDossierRows(dossiers) {
    const tbody = document.getElementById("dossierTableBody");
    if (!tbody) {
        return;
    }

    if (!Array.isArray(dossiers) || dossiers.length === 0) {
        tbody.innerHTML = `<tr><td colspan="7">No dossiers found.</td></tr>`;
        return;
    }

    tbody.innerHTML = dossiers.map((item) => `
        <tr>
            <td>${item.id ?? ""}</td>
            <td>${item.fullName ?? ""}</td>
            <td>${item.fatherName ?? ""}</td>
            <td>${item.phoneNew ?? ""}</td>
            <td>${item.email ?? ""}</td>
            <td>${item.currentAddress ?? ""}</td>
            <td>
                <a class="actionLink" href="edit.html?id=${item.id}">Edit</a>
                <button class="danger mini deleteBtn" data-id="${item.id}" type="button">Delete</button>
            </td>
        </tr>
    `).join("");

    Array.from(document.querySelectorAll(".deleteBtn")).forEach((btn) => {
        btn.addEventListener("click", async () => {
            const id = btn.dataset.id;
            if (!id) {
                return;
            }
            if (!window.confirm(`Delete dossier ID ${id}?`)) {
                return;
            }
            await withOutput(() => request(`/${id}`, { method: "DELETE" }));
            await loadDossiers(currentListPage);
        });
    });
}

function updatePaginationControls() {
    const pageInfo = document.getElementById("pageInfo");
    const prevBtn = document.getElementById("prevPageBtn");
    const nextBtn = document.getElementById("nextPageBtn");
    const totalPages = Math.max(1, Math.ceil(allDossiers.length / PAGE_SIZE));

    if (pageInfo) {
        pageInfo.textContent = `Page ${currentListPage} of ${totalPages} (${allDossiers.length} records)`;
    }
    if (prevBtn) {
        prevBtn.disabled = currentListPage <= 1;
    }
    if (nextBtn) {
        nextBtn.disabled = currentListPage >= totalPages;
    }
}

function renderCurrentPage() {
    const totalPages = Math.max(1, Math.ceil(allDossiers.length / PAGE_SIZE));
    if (currentListPage > totalPages) {
        currentListPage = totalPages;
    }
    if (currentListPage < 1) {
        currentListPage = 1;
    }

    const start = (currentListPage - 1) * PAGE_SIZE;
    const pageRows = allDossiers.slice(start, start + PAGE_SIZE);
    renderDossierRows(pageRows);
    updatePaginationControls();
}

async function loadDossiers(page = 1) {
    const result = await withOutput(() => request("", { method: "GET" }));
    allDossiers = Array.isArray(result) ? result : [];
    currentListPage = page;
    renderCurrentPage();
}

function getEditId() {
    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");
    return id ? id.trim() : "";
}

async function loadDossierForEdit() {
    const id = getEditId();
    const idField = document.getElementById("dossierId");
    if (!id) {
        throw new Error("Missing dossier id in URL. Open edit page as edit.html?id=<id>.");
    }
    if (idField) {
        idField.value = id;
    }
    const result = await withOutput(() => request(`/${id}`, { method: "GET" }));
    const person = result && result.personDossier ? result.personDossier : result;
    writePersonDossierToForm(person);
}

function bindListPage() {
    const refreshBtn = document.getElementById("refreshBtn");
    const prevBtn = document.getElementById("prevPageBtn");
    const nextBtn = document.getElementById("nextPageBtn");

    if (refreshBtn) {
        refreshBtn.addEventListener("click", () => loadDossiers(currentListPage));
    }
    if (prevBtn) {
        prevBtn.addEventListener("click", () => {
            currentListPage -= 1;
            renderCurrentPage();
        });
    }
    if (nextBtn) {
        nextBtn.addEventListener("click", () => {
            currentListPage += 1;
            renderCurrentPage();
        });
    }
    loadDossiers();
}

function bindAddPage() {
    const form = document.getElementById("personForm");
    if (!form) {
        return;
    }
    form.addEventListener("submit", async (event) => {
        event.preventDefault();
        await withOutput(() =>
            request("", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(readPersonDossierFromForm())
            })
        );
        form.reset();
    });
}

function bindEditPage() {
    const form = document.getElementById("personForm");
    const reloadBtn = document.getElementById("reloadEditBtn");

    if (reloadBtn) {
        reloadBtn.addEventListener("click", () => {
            loadDossierForEdit().catch(() => {});
        });
    }

    if (form) {
        form.addEventListener("submit", async (event) => {
            event.preventDefault();
            const id = getEditId();
            if (!id) {
                setOutput("Missing dossier id in URL.");
                return;
            }
            await withOutput(() =>
                request(`/${id}`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(readPersonDossierFromForm())
                })
            );
        });
    }

    loadDossierForEdit().catch(() => {});
}

loadBaseFromLocalStorage();
bindBaseControls();

if (currentPage === "list") {
    bindListPage();
} else if (currentPage === "add") {
    bindAddPage();
} else if (currentPage === "edit") {
    bindEditPage();
}
