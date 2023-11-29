"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const app = (0, express_1.default)();
app.use(express_1.default.json()); // middleware que transforma la req.body en un objeto JSON
const PORT = 3000;
// si la req no se usa poner _req
app.get('/', (_req, res) => {
    console.log("nos saludan!");
    res.send('Hello World!');
});
app.listen(PORT, () => {
    console.log(`⚡️[server]: Server is running at http://localhost:${PORT}`);
});
