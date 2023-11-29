import express from "express";
import swaggerUi from "swagger-ui-express";
import swaggerSetup from "./docs/swagger";

import recommendationsRoutes from "./routes/recommendations";
import mergeRoutes from "./routes/merge";

const app = express();
app.use(express.json()); // middleware que transforma la req.body en un objeto JSON

//app.use("/api-docs", swaggerUi.serve, swaggerUi.setup(swaggerSpec));
app.use("/api-docs", swaggerUi.serve, swaggerUi.setup(swaggerSetup));

const PORT = 3000;

app.get("/", (_req, res) => {
  console.log("nos saludan!");
  res.send("Hello World! hola a todos");
});

//routes
app.use("/api/recommendations", recommendationsRoutes);
app.use("/api/fusion", mergeRoutes);

app.listen(PORT, () => {
  console.log(`⚡️[server]: Server is running at http://localhost:${PORT}`);
});
