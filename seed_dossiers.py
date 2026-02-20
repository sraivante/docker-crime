import json
import sys
from datetime import date
from urllib import request, error

BASE_URL = "http://localhost:8080/dossier/api/v1/dossiers"


def post_json(url: str, payload: dict):
    data = json.dumps(payload).encode("utf-8")
    req = request.Request(
        url,
        data=data,
        headers={"Content-Type": "application/json"},
        method="POST",
    )
    with request.urlopen(req, timeout=10) as resp:
        return resp.status, json.loads(resp.read().decode("utf-8"))


def get_json(url: str):
    req = request.Request(url, method="GET")
    with request.urlopen(req, timeout=10) as resp:
        body = resp.read().decode("utf-8")
        return resp.status, json.loads(body) if body else []


def build_payload(i: int):
    return {
        "fullName": f"Sample Person {i}",
        "fatherName": f"Father {i}",
        "currentAddress": f"Sector {i}, City",
        "permanentAddress": f"Village {i}, District",
        "dateOfBirth": str(date(1990 + (i % 10), (i % 12) + 1, (i % 28) + 1)),
        "religion": "NA",
        "caste": "NA",
        "education": "Graduate",
        "uniqueIdentificationMark": f"Mark-{i}",
        "phoneNew": f"9000000{i:03d}",
        "phoneOld": f"8000000{i:03d}",
        "aadhaarNumber": f"123412341{i:03d}",
        "panNumber": f"ABCDE{i:04d}F",
        "passportNumber": f"P{i:07d}",
        "creditCardDetails": f"card-{i}",
        "bankDetails": f"bank-{i}",
        "drivingLicense": f"DL-{i:06d}",
        "voterId": f"VOTER{i:05d}",
        "otherIdentityDetails": f"Other-{i}",
        "email": f"sample{i}@example.com",
        "photoUrl": f"https://example.com/photo/{i}",
    }


def main():
    start = int(sys.argv[1]) if len(sys.argv) > 1 else 1
    count = int(sys.argv[2]) if len(sys.argv) > 2 else 10
    end = start + count

    created_ids = []
    for i in range(start, end):
        payload = build_payload(i)
        status, body = post_json(BASE_URL, payload)
        created_ids.append(body.get("id"))
        print(f"Created {i}: HTTP {status}, id={body.get('id')}")

    status, all_rows = get_json(BASE_URL)
    print(f"Fetch all: HTTP {status}, total_records={len(all_rows)}")
    print(f"New IDs: {created_ids}")


if __name__ == "__main__":
    try:
        main()
    except error.HTTPError as exc:
        detail = exc.read().decode("utf-8", errors="replace")
        print(f"HTTPError {exc.code}: {detail}")
        raise
    except Exception as exc:
        print(f"Failed: {exc}")
        raise
