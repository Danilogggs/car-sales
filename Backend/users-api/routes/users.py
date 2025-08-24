from flask import Blueprint, request, jsonify
from email_validator import validate_email, EmailNotValidError
from werkzeug.security import generate_password_hash
from datetime import date
from extensions import db
from models.user import User

bp = Blueprint("users", __name__)

@bp.post("/api/users")
def create_user():
    data = request.get_json(force=True) or {}
    name = (data.get("name") or "").strip()
    email = (data.get("email") or "").strip().lower()
    password = data.get("password") or ""

    if not name:
        return jsonify({"error": "name is required"}), 400
    try:
        validate_email(email)
    except EmailNotValidError as e:
        return jsonify({"error": str(e)}), 400
    if not password or len(password) < 6:
        return jsonify({"error": "password must be at least 6 characters"}), 400
    if User.query.filter_by(email=email).first():
        return jsonify({"error": "email already in use"}), 409

    u = User(
        name=name,
        email=email,
        password_hash=generate_password_hash(password),
        phone=data.get("phone"),
        birth_date=date.fromisoformat(data["birthDate"]) if data.get("birthDate") else None,
        photo_url=data.get("photoUrl")
    )
    db.session.add(u)
    db.session.commit()
    return jsonify(u.to_dict()), 201

@bp.get("/api/users/<user_id>")
def get_user(user_id):
    u = User.query.get_or_404(user_id)
    return jsonify(u.to_dict())

@bp.get("/api/users")
def list_users():
    # paginação opcional via query params ?page=1&size=10
    page = int(request.args.get("page", 1))
    size = int(request.args.get("size", 10))

    q = User.query.order_by(User.created_at.desc())
    pag = q.paginate(page=page, per_page=size, error_out=False)

    return {
        "items": [u.to_dict() for u in pag.items],
        "page": page,
        "size": size,
        "total": pag.total,
        "pages": pag.pages,
    }, 200
