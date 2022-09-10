CREATE TABLE "author" (
    "id" BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    "name" VARCHAR(255)
);

CREATE TABLE "book" (
    "id" BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    "title" VARCHAR(255),
    "pages" INT,
    "author_id" BIGINT NOT NULL REFERENCES "author"("id")
);

INSERT INTO "author" ("name") VALUES ('Douglas Adams');
INSERT INTO "book" ("title", "pages", "author_id") VALUES ('Hitchhiker''s Guide To Galaxy', 42, 1);