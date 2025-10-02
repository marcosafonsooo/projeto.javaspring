const apiBase = "http://localhost:8080/cursos"; // ⚡ Ajusta se necessário

const form = document.getElementById("curso-form");
const tableBody = document.getElementById("curso-table-body");
const cancelarBtn = document.getElementById("cancelar-edicao");
const formTitle = document.getElementById("form-title");

let editMode = false;
let editId = null;

// ➕ Criar ou atualizar curso
form.addEventListener("submit", async (e) => {
  e.preventDefault();

  const curso = {
    nome: document.getElementById("nome").value,
    descricao: document.getElementById("descricao").value
  };

  let url = apiBase;
  let method = "POST";

  if (editMode && editId) {
    url = `${apiBase}/${editId}`;
    method = "PUT";
  }

  const resp = await fetch(url, {
    method,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(curso)
  });

  if (resp.ok) {
    alert(editMode ? "Curso atualizado!" : "Curso cadastrado!");
    form.reset();
    cancelarEdicao();
    carregarCursos();
  } else {
    alert("Erro ao salvar curso!");
  }
});

// 📋 Listar cursos
async function carregarCursos() {
  const resp = await fetch(apiBase);
  if (!resp.ok) {
    alert("Erro ao carregar cursos!");
    return;
  }

  const cursos = await resp.json();
  tableBody.innerHTML = "";

  cursos.forEach((curso) => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td data-label="#">${curso.id}</td>
      <td data-label="Nome">${curso.nome}</td>
      <td data-label="Descrição">${curso.descricao}</td>
      <td data-label="Ações">
        <button class="action-btn edit" onclick="editarCurso(${curso.id}, '${curso.nome}', \`${curso.descricao}\`)">Editar</button>
        <button class="action-btn delete" onclick="deletarCurso(${curso.id})">Excluir</button>
      </td>
    `;
    tableBody.appendChild(tr);
  });
}

// ✏️ Editar curso
function editarCurso(id, nome, descricao) {
  document.getElementById("nome").value = nome;
  document.getElementById("descricao").value = descricao;
  editMode = true;
  editId = id;
  formTitle.textContent = "Editar Curso";
  cancelarBtn.hidden = false;
}

// ❌ Cancelar edição
function cancelarEdicao() {
  editMode = false;
  editId = null;
  form.reset();
  formTitle.textContent = "Cadastrar Curso";
  cancelarBtn.hidden = true;
}

cancelarBtn.addEventListener("click", cancelarEdicao);

// 🗑 Deletar curso
async function deletarCurso(id) {
  if (!confirm(`Deseja realmente excluir este curso?`)) return;
  const resp = await fetch(`${apiBase}/${id}`, { method: "DELETE" });
  if (resp.ok) {
    alert("Curso excluído!");
    carregarCursos();
  } else {
    alert("Erro ao excluir curso!");
  }
}

// 🚀 Inicializa
carregarCursos();
