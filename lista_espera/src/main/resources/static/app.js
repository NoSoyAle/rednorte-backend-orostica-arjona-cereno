const API_BASE = "/lista-espera";

const apiStatus = document.querySelector("#apiStatus");
const createForm = document.querySelector("#createForm");
const recordsBody = document.querySelector("#recordsBody");
const message = document.querySelector("#message");
const countBadge = document.querySelector("#countBadge");
const estadoFilter = document.querySelector("#estadoFilter");
const pacienteFilter = document.querySelector("#pacienteFilter");
const especialidadFilter = document.querySelector("#especialidadFilter");
const applyFiltersBtn = document.querySelector("#applyFiltersBtn");
const clearFiltersBtn = document.querySelector("#clearFiltersBtn");
const refreshBtn = document.querySelector("#refreshBtn");
const nextBtn = document.querySelector("#nextBtn");
const nextEspecialidadId = document.querySelector("#nextEspecialidadId");
const assignDialog = document.querySelector("#assignDialog");
const assignForm = document.querySelector("#assignForm");
const cancelAssignBtn = document.querySelector("#cancelAssignBtn");

const estados = ["ESPERANDO", "CONTACTADO", "ASIGNADO", "CANCELADO", "EXPIRADO", "RECHAZADO"];

function showMessage(text, isError = false) {
    message.textContent = text;
    message.classList.toggle("error", isError);
    message.hidden = false;
    window.clearTimeout(showMessage.timeout);
    showMessage.timeout = window.setTimeout(() => {
        message.hidden = true;
    }, 4000);
}

function numberOrNull(value) {
    return value ? Number(value) : null;
}

function formatDate(value) {
    if (!value) {
        return "-";
    }
    return new Date(value).toLocaleString("es-CL");
}

async function request(path = "", options = {}) {
    const response = await fetch(`${API_BASE}${path}`, {
        headers: {
            "Content-Type": "application/json",
            ...(options.headers || {})
        },
        ...options
    });

    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText || `Error HTTP ${response.status}`);
    }

    if (response.status === 204) {
        return null;
    }

    return response.json();
}

function buildFilterPath() {
    const pacienteId = pacienteFilter.value.trim();
    const especialidadId = especialidadFilter.value.trim();
    const estado = estadoFilter.value;

    if (pacienteId) {
        return `/paciente/${pacienteId}`;
    }

    if (especialidadId) {
        return `/especialidad/${especialidadId}${estado ? `?estado=${estado}` : ""}`;
    }

    return estado ? `?estado=${estado}` : "";
}

function renderRows(records) {
    countBadge.textContent = `${records.length} registros`;
    recordsBody.innerHTML = records.map((record) => {
        const priorityLabel = record.prioridad === 1 ? "Alta" : record.prioridad === 2 ? "Media" : "Baja";
        const stateOptions = estados.map((estado) => {
            const selected = estado === record.estado ? "selected" : "";
            return `<option ${selected}>${estado}</option>`;
        }).join("");

        return `
            <tr>
                <td>${record.id}</td>
                <td>${record.pacienteId}</td>
                <td>${record.especialidadId}</td>
                <td>${record.doctorId ?? "-"}</td>
                <td>${record.bloqueHorarioId ?? "-"}</td>
                <td><span class="badge priority-${record.prioridad}">${priorityLabel}</span></td>
                <td><span class="badge">${record.estado}</span></td>
                <td>${formatDate(record.fechaIngreso)}</td>
                <td>${record.motivo || "-"}</td>
                <td>
                    <div class="row-actions">
                        <select data-id="${record.id}" class="state-select">${stateOptions}</select>
                        <button type="button" data-id="${record.id}" class="assign-btn">Asignar</button>
                        <button type="button" data-id="${record.id}" class="delete-btn danger">Eliminar</button>
                    </div>
                </td>
            </tr>
        `;
    }).join("");
}

async function loadRecords() {
    try {
        const records = await request(buildFilterPath());
        renderRows(Array.isArray(records) ? records : [records]);
        apiStatus.textContent = "API conectada";
        apiStatus.className = "api-status ok";
    } catch (error) {
        apiStatus.textContent = "API sin conexion";
        apiStatus.className = "api-status error";
        showMessage(error.message, true);
    }
}

createForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    const formData = new FormData(createForm);
    const body = {
        pacienteId: Number(formData.get("pacienteId")),
        especialidadId: Number(formData.get("especialidadId")),
        doctorId: numberOrNull(formData.get("doctorId")),
        prioridad: Number(formData.get("prioridad")),
        motivo: formData.get("motivo"),
        observaciones: formData.get("observaciones")
    };

    try {
        await request("", {
            method: "POST",
            body: JSON.stringify(body)
        });
        createForm.reset();
        showMessage("Solicitud creada correctamente");
        await loadRecords();
    } catch (error) {
        showMessage(error.message, true);
    }
});

recordsBody.addEventListener("change", async (event) => {
    if (!event.target.classList.contains("state-select")) {
        return;
    }

    try {
        await request(`/${event.target.dataset.id}/estado`, {
            method: "PUT",
            body: JSON.stringify({
                estado: event.target.value,
                observaciones: `Estado actualizado a ${event.target.value}`
            })
        });
        showMessage("Estado actualizado");
        await loadRecords();
    } catch (error) {
        showMessage(error.message, true);
    }
});

recordsBody.addEventListener("click", async (event) => {
    const id = event.target.dataset.id;
    if (!id) {
        return;
    }

    if (event.target.classList.contains("assign-btn")) {
        assignForm.elements.id.value = id;
        assignDialog.showModal();
        return;
    }

    if (event.target.classList.contains("delete-btn")) {
        try {
            await request(`/${id}`, { method: "DELETE" });
            showMessage("Solicitud eliminada");
            await loadRecords();
        } catch (error) {
            showMessage(error.message, true);
        }
    }
});

assignForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    const formData = new FormData(assignForm);

    try {
        await request(`/${formData.get("id")}/asignar`, {
            method: "PUT",
            body: JSON.stringify({
                doctorId: Number(formData.get("doctorId")),
                bloqueHorarioId: Number(formData.get("bloqueHorarioId")),
                observaciones: formData.get("observaciones")
            })
        });
        assignDialog.close();
        assignForm.reset();
        showMessage("Hora asignada correctamente");
        await loadRecords();
    } catch (error) {
        showMessage(error.message, true);
    }
});

nextBtn.addEventListener("click", async () => {
    const especialidadId = nextEspecialidadId.value.trim();
    if (!especialidadId) {
        showMessage("Ingresa una especialidad para buscar el siguiente paciente", true);
        return;
    }

    try {
        const record = await request(`/especialidad/${especialidadId}/siguiente`);
        renderRows([record]);
        showMessage(`Siguiente paciente encontrado: solicitud ${record.id}`);
    } catch (error) {
        showMessage(error.message, true);
    }
});

cancelAssignBtn.addEventListener("click", () => {
    assignDialog.close();
});

applyFiltersBtn.addEventListener("click", loadRecords);
clearFiltersBtn.addEventListener("click", () => {
    estadoFilter.value = "";
    pacienteFilter.value = "";
    especialidadFilter.value = "";
    loadRecords();
});
refreshBtn.addEventListener("click", loadRecords);

loadRecords();
