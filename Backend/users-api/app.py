from flask import Flask
from dotenv import load_dotenv
from extensions import db, migrate
from config import Config
from routes.users import bp as users_bp
from models import user 
load_dotenv()

def create_app():
    app = Flask(__name__)
    app.config.from_object(Config)

    db.init_app(app)
    migrate.init_app(app, db)

    app.register_blueprint(users_bp)

    @app.get("/health")
    def health():
        return {"status": "ok"}, 200

    return app

app = create_app()

if __name__ == "__main__":
    app.run(debug=True, port=5001)
