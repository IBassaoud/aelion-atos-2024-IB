# Env de base
FROM python:3.12.0-slim-bookworm

WORKDIR /app

# COPY src/* /app
COPY src/ .

RUN pip install -r requirements.txt

# Container process : RUN pip install
CMD [ "flask", "--app", "index", "run" ]