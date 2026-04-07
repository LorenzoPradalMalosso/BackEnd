// Importa framework
const express = require("express");

// Cria a aplicação express
const app = express();

// Define a porta
const PORT = 3000;

// Rota
app.get("/hello", (req, res) => {
    res.json({
        message: "Ola, Mundo!"
    });
});

app.use((req, res) => {
    res.status(404).json({
        error: "Rota não encontrada"
    });
});

app.listen(PORT, () => {
    console.log(`Servidor rodando em http://localhost:${PORT}`);
})