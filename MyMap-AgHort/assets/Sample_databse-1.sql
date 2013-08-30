DROP TABLE IF EXISTS "android_metadata";
CREATE TABLE "android_metadata" ("locale" TEXT DEFAULT 'en_US');
INSERT INTO "android_metadata" VALUES('en_US');
DROP TABLE IF EXISTS "data";
CREATE TABLE "data" ("room" TEXT,"_id" NUMERIC);
INSERT INTO "data" VALUES('a0:21:b7:70:b4:cc',1);
INSERT INTO "data" VALUES('62:54:99:d6:0b:f0',2);
