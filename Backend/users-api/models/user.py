from datetime import datetime
import uuid
from extensions import db

def _uuid() -> str:
    return str(uuid.uuid4())

class User(db.Model):
    __tablename__ = "users"

    id = db.Column(db.String(36), primary_key=True, default=_uuid)
    name = db.Column(db.String(120), nullable=False)
    email = db.Column(db.String(255), nullable=False, unique=True, index=True)
    phone = db.Column(db.String(30))
    birth_date = db.Column(db.Date)
    photo_url = db.Column(db.Text)
    password_hash = db.Column(db.String(255), nullable=False)

    created_at = db.Column(db.DateTime, default=datetime.utcnow, nullable=False)
    updated_at = db.Column(db.DateTime, default=datetime.utcnow,
                           onupdate=datetime.utcnow, nullable=False)

    def to_dict(self, include_hash: bool = False):
        data = {
        "id": self.id,
        "name": self.name,
        "email": self.email,
        "phone": self.phone,
        "birthDate": self.birth_date.isoformat() if self.birth_date else None,
        "photoUrl": self.photo_url,
        "createdAt": self.created_at.isoformat(),
        "updatedAt": self.updated_at.isoformat(),
    }
        if include_hash:
            data["password_hash"] = self.password_hash
        return data


