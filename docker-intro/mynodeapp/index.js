"use strict";

const express = require("express");
const path = require("path");

// Constants
const PORT = 80;
const HOST = "0.0.0.0";

// App
const app = express();

// Serve static files
app.use(express.static(path.resolve(__dirname)));

// Serve index.html at the root route
app.get("/", (req, res) => {
  res.sendFile(path.resolve(__dirname, "index.html"), (err) => {
    if (err) {
      res.status(500).send("Error serving the file");
    }
  });
});

app.listen(PORT, HOST, () => {
  console.log(`Running on http://${HOST}:${PORT}`);
});
