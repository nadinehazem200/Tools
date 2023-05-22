BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Restaurant" (
	"id"	INTEGER NOT NULL,
	"name"	TEXT,
	"ownerid"	INTEGER,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "RestaurantOwner" (
	"id"	INTEGER NOT NULL,
	"name"	TEXT NOT NULL,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Runner" (
	"ID"	INT NOT NULL,
	"NAME"	TEXT NOT NULL,
	"Status"	TEXT,
	"delivery fees"	REAL NOT NULL,
	PRIMARY KEY("ID")
);
CREATE TABLE IF NOT EXISTS "Customer" (
	"id"	INTEGER NOT NULL,
	"name"	TEXT NOT NULL,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Meal" (
	"id"	INTEGER NOT NULL,
	"name"	TEXT NOT NULL,
	"price"	REAL,
	"fk_restaurantId"	INTEGER NOT NULL,
	FOREIGN KEY("fk_restaurantId") REFERENCES "Restaurant"("id"),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "OrderMealsDetails" (
	"id"	INTEGER NOT NULL,
	"orderId"	INTEGER NOT NULL,
	"mealId"	INTEGER NOT NULL,
	FOREIGN KEY("mealId") REFERENCES "Meal"("id"),
	FOREIGN KEY("orderId") REFERENCES "Order"("id"),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "Order" (
	"id"	INTEGER NOT NULL,
	"total price"	REAL NOT NULL,
	"status"	TEXT,
	"fk_runnerId"	INTEGER NOT NULL,
	"fk_restaurantId"	INTEGER NOT NULL,
	"fk_customerId"	INTEGER NOT NULL,
	"date"	TEXT,
	FOREIGN KEY("fk_customerId") REFERENCES "Customer"("id"),
	FOREIGN KEY("fk_restaurantId") REFERENCES "Restaurant"("id"),
	FOREIGN KEY("fk_runnerId") REFERENCES "Runner"("ID"),
	PRIMARY KEY("id")
);
COMMIT;
