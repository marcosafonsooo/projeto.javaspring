const alunosAPI = "http://localhost:8080/alunos";
const cursosAPI = "http://localhost:8080/cursos";

const alunoSelect = document.getElementById("aluno-select");
const cursoSelect = document.getElementById("curso-select");
const matriculaForm = document.getElementById("matricula-form");
const tableBody = document.getElementById("matricula-table-body");

// 🚀 Inicialização
carregarAlunos();
carregarCursos();
carregarTabela();

// 🧍 Carregar lista de alunos
async function carregarAlunos() {
  const resp = await fetch(alunosAPI);
  if (!resp.ok) {
    alert("Erro ao carregar alunos!");
    return;
  }
  const alunos = await resp.json();
  alunoSelect.innerHTML = `<option value="">Selecione um aluno</option>`;
  alunos.forEach(a => {
    const opt = document.createElement("option");
    opt.value = a.id;
    opt.textContent = `${a.nome} (${a.email})`;
    alunoSelect.appendChild(opt);
  });
}

// 📚 Carregar lista de cursos
async function carregarCursos() {
  const resp = await fetch(cursosAPI);
  if (!resp.ok) {
    alert("Erro ao carregar cursos!");
    return;
  }
  const cursos = await resp.json();
  cursoSelect.innerHTML = `<option value="">Selecione um curso</option>`;
  cursos.forEach(c => {
    const opt = document.createElement("option");
    opt.value = c.id;
    opt.textContent = c.nome;
    cursoSelect.appendChild(opt);
  });
}

// 📝 Matricular aluno em curso
matriculaForm.addEventListener("submit", async (e) => {
  e.preventDefault(); // impede o refresh da página

  const alunoId = alunoSelect.value;
  const cursoId = cursoSelect.value;

  if (!alunoId || !cursoId) {
    alert("Selecione um aluno e um curso.");
    return;
  }

  const url = `${alunosAPI}/${alunoId}/matricular/${cursoId}`;
  const resp = await fetch(url, { method: "PUT" });

  if (resp.ok) {
    alert("Matrícula realizada com sucesso!");
    carregarTabela();
    matriculaForm.reset();
  } else {
    alert("Erro ao matricular aluno!");
  }
});

// 📊 Carregar tabela de alunos e seus cursos
async function carregarTabela() {
  const resp = await fetch(alunosAPI);
  if (!resp.ok) {
    alert("Erro ao carregar tabela de matrículas!");
    return;
  }

  const alunos = await resp.json();
  tableBody.innerHTML = "";

  alunos.forEach(aluno => {
    const cursos = aluno.cursos && aluno.cursos.length > 0
      ? aluno.cursos.map(c => c.nome).join(", ")
      : "Nenhum";

    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td data-label="ID">${aluno.id}</td>
      <td data-label="Aluno">${aluno.nome}</td>
      <td data-label="Curso(s)">${cursos}</td>
    `;
    tableBody.appendChild(tr);
  });
}
