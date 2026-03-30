export interface Job{
    id: number;
    title: string;
    company: string;
    description: string;
    location: string;
    skills: string[];
}

export interface Candidate{
    cpf: number;
    name: string;
    email: string;
    age: number;
    state: string;
    cep: number;
    course: string;
    description: string;
    skills: string[];
}

export interface Company{
    cnpj: number;
    name: string;
    email: string;
    country: string;
    state: string;
    cep: number;
    description: string;
}