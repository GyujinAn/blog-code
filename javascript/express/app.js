const express = require("express");
const app = express();

app.use(express.json()); // Middleware to parse JSON bodies

// Define routes
app.get("/", (req, res) => {
  res.send("Hello World!");
});

// Start server
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));

// GET method route
app.get("/api/data", (req, res) => {
  res.status(200).send({ message: "Fetching data" });
});

// POST method route
app.post("/api/data", (req, res) => {
  const data = req.body;
  // Add data to the database or perform other processing
  res.status(201).send({ message: "Data created", data });
});

// PUT method route
app.put("/api/data/:id", (req, res) => {
  const { id } = req.params;
  const updates = req.body;
  // Update data based on id
  res.status(200).send({ message: "Data updated", id, updates });
});

// DELETE method route
app.delete("/api/data/:id", (req, res) => {
  const { id } = req.params;
  // Delete data based on id
  res.status(200).send({ message: "Data deleted", id });
});
