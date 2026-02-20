from urllib import request

url = "http://localhost:8080/dossier/app.js"
text = request.urlopen(url, timeout=10).read().decode("utf-8")
print("HAS_PAGE_SIZE", "const PAGE_SIZE = 10" in text)
print("HAS_PAGINATION_RENDER", "renderCurrentPage" in text)
