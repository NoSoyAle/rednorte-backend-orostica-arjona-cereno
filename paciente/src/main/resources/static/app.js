const menuToggle = document.querySelector(".menu-toggle");
const mainMenu = document.querySelector(".main-menu");
const waitItems = document.querySelectorAll(".wait-item");
const progressTitle = document.querySelector("#progress-title");
const statusPill = document.querySelector(".status-pill");
const progressFill = document.querySelector(".progress-fill");
const lookupForm = document.querySelector(".code-lookup");
const trackingInput = document.querySelector("#tracking-code");
const lookupMessage = document.querySelector(".lookup-message");
const currentCode = document.querySelector(".current-code");

const requests = {
    "RN-4821": {
        title: "Hora a medico especialista",
        state: "En preparacion",
        progress: 68
    },
    "RN-3057": {
        title: "Hora a examenes",
        state: "Revision pendiente",
        progress: 42
    },
    "RN-9184": {
        title: "Interconsulta",
        state: "Solicitud recibida",
        progress: 25
    }
};

function showRequest(code, request) {
    progressTitle.textContent = request.title;
    statusPill.textContent = request.state;
    progressFill.style.width = `${request.progress}%`;
    currentCode.textContent = code;

    waitItems.forEach((item) => {
        item.classList.toggle("selected", item.dataset.code === code);
    });
}

menuToggle?.addEventListener("click", () => {
    const isOpen = mainMenu.classList.toggle("open");
    menuToggle.setAttribute("aria-expanded", String(isOpen));
    menuToggle.setAttribute("aria-label", isOpen ? "Cerrar menu" : "Abrir menu");
});

mainMenu?.querySelectorAll("a").forEach((link) => {
    link.addEventListener("click", () => {
        mainMenu.classList.remove("open");
        menuToggle?.setAttribute("aria-expanded", "false");
        menuToggle?.setAttribute("aria-label", "Abrir menu");
    });
});

waitItems.forEach((item) => {
    item.addEventListener("click", () => {
        const code = item.dataset.code;
        const request = requests[code];

        if (!request) {
            return;
        }

        trackingInput.value = code;
        lookupMessage.textContent = `Solicitud encontrada para el codigo ${code}.`;
        lookupMessage.className = "lookup-message success";
        showRequest(code, request);
    });
});

lookupForm?.addEventListener("submit", (event) => {
    event.preventDefault();

    const code = trackingInput.value.trim().toUpperCase();
    const request = requests[code];

    if (!code) {
        lookupMessage.textContent = "Ingresa el codigo entregado por el centro de salud.";
        lookupMessage.className = "lookup-message error";
        trackingInput.focus();
        return;
    }

    if (!request) {
        lookupMessage.textContent = "No encontramos una solicitud asociada a ese codigo.";
        lookupMessage.className = "lookup-message error";
        return;
    }

    trackingInput.value = code;
    lookupMessage.textContent = `Solicitud encontrada para el codigo ${code}.`;
    lookupMessage.className = "lookup-message success";
    showRequest(code, request);
});
