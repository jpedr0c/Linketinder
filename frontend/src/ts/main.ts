const modalNewJob = document.querySelector(".modalNovaVaga") as HTMLElement | null;
const modalContent = document.querySelector(".modalContent") as HTMLElement | null;
const btnNewJob = document.getElementById("btnNewJob") as HTMLButtonElement | null;
const btnClose = document.getElementById("btnClose") as HTMLButtonElement | null;

const form = document.querySelector(".modalContent form") as HTMLFormElement | null;
const inputTitle = document.getElementById("title") as HTMLInputElement | null;
const inputDescription = document.getElementById("description") as HTMLTextAreaElement | null;
const inputLocation = document.getElementById("location") as HTMLInputElement | null;

const inputSkill = document.getElementById("skills") as HTMLInputElement | null;
const btnAddSkill = document.querySelector(".inputSkills button") as HTMLButtonElement | null;
const tagsContainer = document.querySelector(".tags") as HTMLElement | null;

const profileCompany = document.getElementById("companyProfile") as HTMLElement | null;
const profileCandidate = document.getElementById("candidateProfile") as HTMLElement | null;

let skills: string[] = [];

const candidateData = {
    name: "João Pedro Cardoso",
    email: "jocardos@email.com",
    cnpj: "11.111.111/0001-11",
    age: "24",
    state: "RJ",
    cep: "04000-000",
    course: "Análise e desenvolvimento de sistemas",
    description: "Empresa líder em soluções de software.",
    skills: ["Java", "Angular", "Spring"]
};

const companyData = {
    name: "ZG Soluções",
    email: "rh@zgsolucoes.com",
    cnpj: "14.488.144/0001-43",
    country: "Brasil",
    state: "GO",
    cep: "74070-040",
    description: "Empresa líder em soluções de software.",
};

function renderCompanyProfile() {
    if (!profileCompany) return;

    profileCompany.innerHTML = `
        <div class="cardForm">
            <div class="titleForm">
                <h3>Perfil da empresa</h3>
            </div>

            <form>
                <div class="w50">
                    <div class="inputForm">
                        <label>Nome da empresa</label>
                        <input type="text" value="${companyData.name}" disabled />
                    </div>

                    <div class="inputForm">
                        <label>Email</label>
                        <input type="text" value="${companyData.email}" disabled />
                    </div>
                </div>

                <div class="w50">
                    <div class="inputForm">
                        <label>CNPJ</label>
                        <input type="text" value="${companyData.cnpj}" disabled />
                    </div>

                    <div class="inputForm">
                        <label>País</label>
                        <input type="text" value="${companyData.country}" disabled />
                    </div>
                </div>

                <div class="w50">
                    <div class="inputForm">
                        <label>Estado</label>
                        <input type="text" value="${companyData.state}" disabled />
                    </div>

                    <div class="inputForm">
                        <label>CEP</label>
                        <input type="text" value="${companyData.cep}" disabled />
                    </div>
                </div>

                <div class="inputForm">
                    <label>Descrição</label>
                    <textarea disabled>${companyData.description}</textarea>
                </div>

                <button type="button">Salvar perfil</button>
            </form>
        </div>
    `;
}

function renderCandidateProfile() {
    if (!profileCandidate) return;

    profileCandidate.innerHTML = `
        <div class="cardForm">
        <div class="titleForm">
            <h3>Meu Perfil</h3>
        </div>
        <form action="">
            <div class="w50">
                <div class="inputForm">
                    <label for="name">Nome</label>
                    <input
                            type="text"
                            id="name"
                            placeholder="João Pedro"
                            disabled
                    />
                </div>
                <div class="inputForm">
                    <label for="email">Email</label>
                    <input
                            type="text"
                            id="email"
                            placeholder="joaopedro@email.com"
                            disabled
                    />
                </div>
            </div>

            <div class="w50">
                <div class="inputForm">
                    <label for="cpf">CPF</label>
                    <input
                            type="number"
                            id="cpf"
                            placeholder="111.123.456-00"
                            disabled
                    />
                </div>
                <div class="inputForm">
                    <label for="idade">Idade</label>
                    <input
                            type="number"
                            id="idade"
                            placeholder="24"
                            disabled
                    />
                </div>
            </div>

            <div class="w50">
                <div class="inputForm">
                    <label for="estado">Estado</label>
                    <input
                            type="text"
                            id="estado"
                            placeholder="RJ"
                            disabled
                    />
                </div>
                <div class="inputForm">
                    <label for="cep">CEP</label>
                    <input
                            type="number"
                            id="cep"
                            placeholder="23456-000"
                            disabled
                    />
                </div>
            </div>

            <div class="inputForm">
                <label for="formacao">Formação</label>
                <input
                        type="text"
                        id="formacao"
                        placeholder="Análise e desenvolvimento de sistemas - UNINTER"
                        disabled
                />
            </div>

            <div class="inputForm">
                <label for="description">Descrição</label>
                <textarea id="description" placeholder="Desenvolvedor fullstack apaixonado por tecnologia." disabled></textarea>
            </div>

            <div class="inputForm">
                <label for="skills">Competências</label>
                <div class="inputSkills">
                    <input
                            type="text"
                            id="skills"
                            placeholder="Ex: Java"
                    />
                    <button>Adicionar</button>
                </div>
                <div class="tags">
                    <div class="tag">
                        <span>Python</span>
                        <button>X</button>
                    </div>
                    <div class="tag">
                        <span>Java</span>
                        <button>X</button>
                    </div>
                    <div class="tag">
                        <span>Angular</span>
                        <button>X</button>
                    </div>
                </div>
            </div>

            <button>Salvar perfil</button>
        </form>
    </div>
    `;
}

renderCompanyProfile();
renderCandidateProfile();

btnNewJob?.addEventListener("click", () => createNewJob());
btnClose?.addEventListener("click", closeModal);

function createNewJob() {
    modalNewJob?.classList.add("show");
}

function closeModal() {
    modalNewJob?.classList.remove("show");
    resetForm();
}

function handleOutsideClick(e: MouseEvent) {
    if (!modalContent?.contains(e.target as Node)) {
        closeModal();
    }
}

function closeModalOutsideClick() {
    modalNewJob?.addEventListener("click", handleOutsideClick);
}

closeModalOutsideClick();

function resetForm() {
    form?.reset();
    skills = [];
    if (tagsContainer) {
        tagsContainer.innerHTML = "";
    }
}
btnAddSkill?.addEventListener("click", (e) => {
    e.preventDefault();

    const skill = inputSkill?.value.trim();
    if (!skill) return;

    skills.push(skill);
    renderSkills();

    if (inputSkill) inputSkill.value = "";
});

function renderSkills() {
    if (!tagsContainer) return;

    tagsContainer.innerHTML = "";

    skills.forEach((skill, index) => {
        const tag = document.createElement("div");
        tag.classList.add("tag");

        tag.innerHTML = `
            <span>${skill}</span>
            <button id="btnRemoveTag" data-index="${index}">X</button>
        `;

        const btnRemove = tag.querySelector("#btnRemoveTag") as HTMLButtonElement;
        btnRemove.addEventListener("click", () => {
            skills.splice(index, 1);
            console.log(skills);
            renderSkills();
        });

        tagsContainer.appendChild(tag);
    });
}

form?.addEventListener("submit", (e) => {
    e.preventDefault();

    const newJob = {
        id: Date.now().toString(),
        title: inputTitle?.value || "",
        description: inputDescription?.value || "",
        location: inputLocation?.value || "",
        skills: skills,
    };

    const jobs = JSON.parse(localStorage.getItem("jobs") || "[]");

    jobs.push(newJob);

    localStorage.setItem("jobs", JSON.stringify(jobs));

    console.log("Vaga salva:", newJob);

    closeModal();
});