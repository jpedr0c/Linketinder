INSERT INTO competencia (nome) VALUES
    ('Java'),
    ('Python'),
    ('Spring'),
    ('Angular'),
    ('Docker'),
    ('SQL'),
    ('Typescript'),
    ('Kafka'),
    ('Kubernetes'),
    ('Grails');

INSERT INTO candidato (nome, sobrenome, data_nascimento, email, telefone, cpf, pais, estado, cidade, cep, descricao, linkedin, senha) VALUES
    ('João', 'Silva', '1995-03-15', 'joao.silva@email.com', '21999990001', '11111111111', 'Brasil', 'RJ', 'Rio de Janeiro', '20010000', 'Desenvolvedor backend com foco em Java', 'linkedin.com/in/joaosilva', 'senha123'),

    ('Maria', 'Souza', '1998-07-22', 'maria.souza@email.com', '11988880002', '22222222222', 'Brasil', 'SP', 'São Paulo', '01020000', 'Frontend especializada em Angular', 'linkedin.com/in/mariasouza', 'senha456'),

    ('Carlos', 'Oliveira', '1992-11-05', 'carlos.oliveira@email.com', '31977770003', '33333333333', 'Brasil', 'MG', 'Belo Horizonte', '30110000', 'Fullstack com experiência em Node.js', 'linkedin.com/in/carlosoliveira', 'senha789'),

    ('Ana', 'Costa', '1996-01-30', 'ana.costa@email.com', '41966660004', '44444444444', 'Brasil', 'PR', 'Curitiba', '80020000', 'DevOps com foco em Docker e Kubernetes', 'linkedin.com/in/anacosta', 'senha321'),

    ('Pedro', 'Santos', '1994-09-18', 'pedro.santos@email.com', '71955550005', '55555555555', 'Brasil', 'BA', 'Salvador', '40030000', 'Desenvolvedor Python com foco em APIs', 'linkedin.com/in/pedrosantos', 'senha654');

INSERT INTO empresa (nome, email, cnpj, pais, estado, cidade, cep, descricao, senha) VALUES
    ('TechSoft', 'contato@techsoft.com', '11111111000101', 'Brasil', 'SP', 'São Paulo', '01000000', 'Empresa focada em soluções corporativas', 'empresa123'),

    ('InovaTech', 'rh@inovatech.com', '22222222000102', 'Brasil', 'RJ', 'Rio de Janeiro', '20000000', 'Startup de inovação tecnológica', 'empresa456'),

    ('CodeMaster', 'jobs@codemaster.com', '33333333000103', 'Brasil', 'MG', 'Belo Horizonte', '30000000', 'Especialistas em desenvolvimento sob medida', 'empresa789'),

    ('DevSolutions', 'contato@devsolutions.com', '44444444000104', 'Brasil', 'PR', 'Curitiba', '80000000', 'Consultoria em TI e cloud', 'empresa321'),

    ('FutureSystems', 'recrutamento@future.com', '55555555000105', 'Brasil', 'SC', 'Florianópolis', '88000000', 'Empresa focada em soluções escaláveis', 'empresa654');

INSERT INTO vaga (empresa_id, nome, cidade, estado, descricao) VALUES
    (1, 'Desenvolvedor Java Backend', 'São Paulo', 'SP', 'Atuar com Java e Spring Boot'),

    (2, 'Frontend Angular Pleno', 'Rio de Janeiro', 'RJ', 'Desenvolvimento de interfaces modernas'),

    (3, 'Fullstack Node.js', 'Belo Horizonte', 'MG', 'Atuação em backend e frontend'),

    (4, 'Engenheiro DevOps', 'Curitiba', 'PR', 'Automação e infraestrutura cloud'),

    (5, 'Desenvolvedor Python', 'Florianópolis', 'SC', 'Construção de APIs REST');

INSERT INTO candidato_competencia VALUES
    (1, 1), (1, 3), (1, 6),
    (2, 4), (2, 7), (2, 8),
    (3, 7), (3, 10), (3, 6),
    (4, 5), (4, 9),
    (5, 2), (5, 6);

INSERT INTO vaga_competencia VALUES
    (1, 1), (1, 3),
    (2, 4), (2, 7),
    (3, 7), (3, 10),
    (4, 5), (4, 9),
    (5, 2), (5, 6);