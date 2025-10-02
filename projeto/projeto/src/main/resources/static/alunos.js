const apiBase = "/alunos";

const form = document.getElementById("aluno-form");
const tableBody = document.getElementById("aluno-table-body");
const cancelarBtn = document.getElementById("cancelar-edicao");
const formTitle = document.getElementById("form-title");
let editMode = false;
let editId = null;

// 🟢 Criar ou atualizar aluno
form.addEventListener("submit", async (e) => {
  e.preventDefault();

  const aluno = {
    nome: document.getElementById("nome").value,
    email: document.getElementById("email").value,
    senha: document.getElementById("senha").value
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
    body: JSON.stringify(aluno)
  });

  if (resp.ok) {
    alert(editMode ? "Aluno atualizado com sucesso!" : "Aluno cadastrado com sucesso!");
    form.reset();
    cancelarEdicao();
    carregarAlunos();
  } else {
    alert("Erro ao salvar aluno!");
  }
});

// 📋 Carregar lista de alunos
async function carregarAlunos() {
  const resp = await fetch(apiBase);
  if (!resp.ok) {
    alert("Erro ao carregar alunos!");
    return;
  }

  const alunos = await resp.json();
  tableBody.innerHTML = "";

  alunos.forEach((aluno, index) => {
    const tr = document.createElement("tr");

    tr.innerHTML = `
      <td data-label="#">${aluno.id}</td>
      <td data-label="Nome">${aluno.nome}</td>
      <td data-label="Email">${aluno.email}</td>
      <td data-label="Ações">
        <button class="action-btn edit" onclick="editarAluno(${aluno.id}, '${aluno.nome}', '${aluno.email}')">Editar</button>
        <button class="action-btn delete" onclick="deletarAluno('${aluno.email}')">Excluir</button>
      </td>
    `;

    tableBody.appendChild(tr);
  });
}

// ✏️ Preencher form para edição
function editarAluno(id, nome, email) {
  document.getElementById("nome").value = nome;
  document.getElementById("email").value = email;
  document.getElementById("senha").value = "";

  editMode = true;
  editId = id;
  formTitle.textContent = "Editar Aluno";
  cancelarBtn.hidden = false;
}

// ❌ Cancelar edição
function cancelarEdicao() {
  editMode = false;
  editId = null;
  form.reset();
  formTitle.textContent = "Cadastrar Aluno";
  cancelarBtn.hidden = true;
}

cancelarBtn.addEventListener("click", cancelarEdicao);

// 🗑 Deletar aluno
async function deletarAluno(email) {
  if (!confirm(`Deseja realmente excluir o aluno ${email}?`)) return;

  const resp = await fetch(`${apiBase}?email=${email}`, { method: "DELETE" });
  if (resp.ok) {
    alert("Aluno deletado com sucesso!");
    carregarAlunos();
  } else {
    alert("Erro ao deletar aluno!");
  }
}

// 🚀 Inicializa
carregarAlunos();
