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

const PATTERNS = {
    name: /^[A-Za-zÀ-ÿ\s]{3,}$/,
    email: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
    cpf: /^\d{3}\.\d{3}\.\d{3}-\d{2}$/,
    cnpj: /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/,
    phone: /^\(\d{2}\)\s\d{4,5}-\d{4}$/,
    cep: /^\d{5}-\d{3}$/,
    linkedin: /^https:\/\/(www\.)?linkedin\.com\/.*$/,
};

const candidateData = {
    name: "João Pedro Cardoso",
    email: "jocardos@email.com",
    phone: "(21) 99324-0325",
    cpf: "341.523.522-52",
    age: "24",
    state: "RJ",
    cep: "04000-000",
    linkedin: "https://www.linkedin.com/in/jpedroc/",
    course: "Análise e desenvolvimento de sistemas",
    description: "Desenvolvedor fullstack",
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

function showError(input: HTMLElement | null, message: string) {
    if (!input) return;

    input.classList.add("error");

    let errorSpan = input.parentElement?.querySelector(".error-message") as HTMLElement;

    if (!errorSpan) {
        errorSpan = document.createElement("span");
        errorSpan.classList.add("error-message");
        input.parentElement?.appendChild(errorSpan);
    }

    errorSpan.textContent = message;
}

function clearError(input: HTMLElement | null) {
    if (!input) return;

    input.classList.remove("error");

    const errorSpan = input.parentElement?.querySelector(".error-message");
    if (errorSpan) errorSpan.remove();
}

function validateCandidate(data: any) {
    let valid = true;

    if (!PATTERNS.name.test(data.name)) {
        alert("Nome inválido");
        valid = false;
    }

    if (!PATTERNS.email.test(data.email)) {
        alert("Email inválido");
        valid = false;
    }

    if (!PATTERNS.cpf.test(data.cpf)) {
        alert("CPF inválido");
        valid = false;
    }

    if (!PATTERNS.phone.test(data.phone)) {
        alert("Telefone inválido");
        valid = false;
    }

    if (!PATTERNS.linkedin.test(data.linkedin)) {
        alert("Link do Linkedin inválido");
        valid = false;
    }

    return valid;
}

validateCandidate(candidateData);

function validateCompany(data: any) {
    let valid = true;

    if (!PATTERNS.name.test(data.name)) {
        console.log("Nome inválido");
        valid = false;
    }

    if (!PATTERNS.email.test(data.email)) {
        console.log("Email inválido");
        valid = false;
    }

    if (!PATTERNS.cnpj.test(data.cnpj)) {
        console.log("CNPJ inválido");
        valid = false;
    }

    if (!PATTERNS.cep.test(data.cep)) {
        console.log("CEP inválido");
        valid = false;
    }

    return valid;
}

validateCompany(companyData);

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
                            value="${candidateData.name}"
                            disabled
                    />
                </div>
                <div class="inputForm">
                    <label for="email">Email</label>
                    <input
                            type="text"
                            id="email"
                            value="${candidateData.email}"
                            disabled
                    />
                </div>
            </div>

            <div class="w50">
                <div class="inputForm">
                    <label for="cpf">CPF</label>
                    <input
                            type="text"
                            id="cpf"
                            value="${candidateData.cpf}"
                            disabled
                    />
                </div>
                <div class="inputForm">
                    <label for="idade">Idade</label>
                    <input
                            type="text"
                            id="idade"
                            value="${candidateData.age}"
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
                            value="${candidateData.state}"
                            disabled
                    />
                </div>
                <div class="inputForm">
                    <label for="cep">CEP</label>
                    <input
                            type="text"
                            id="cep"
                            value="${candidateData.cep}"
                            disabled
                    />
                </div>
            </div>

            <div class="inputForm">
                <label for="formacao">Formação</label>
                <input
                        type="text"
                        id="formacao"
                        value="${candidateData.course}"
                        disabled
                />
            </div>

            <div class="inputForm">
                <label for="description">Descrição</label>
                <textarea id="description" disabled>${candidateData.description}</textarea>
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

    let isValid = true;

    // TITLE
    if (!inputTitle?.value || inputTitle.value.length < 3) {
        showError(inputTitle, "Título deve ter no mínimo 3 caracteres");
        isValid = false;
    } else {
        clearError(inputTitle);
    }

    // LOCATION
    if (!inputLocation?.value || inputLocation.value.length < 3) {
        showError(inputLocation, "Localização inválida");
        isValid = false;
    } else {
        clearError(inputLocation);
    }

    // SKILLS
    if (skills.length === 0) {
        showError(inputSkill, "Adicione pelo menos uma competência");
        isValid = false;
    } else {
        clearError(inputSkill);
    }

    if (!isValid) return;

    if (!inputTitle || !inputLocation) return;

    const newJob = {
        id: Date.now().toString(),
        title: inputTitle.value,
        description: inputDescription?.value || "",
        location: inputLocation.value,
        skills: skills,
    };

    const jobs = JSON.parse(localStorage.getItem("jobs") || "[]");

    jobs.push(newJob);
    localStorage.setItem("jobs", JSON.stringify(jobs));

    console.log("Vaga salva:", newJob);

    closeModal();
});