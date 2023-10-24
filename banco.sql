CREATE TABLE IF NOT EXISTS faixaSalarial (
  idFaixaSalarial int not null,
  salario decimal(6, 2)not null,

  PRIMARY KEY(idFaixaSalarial)
);

CREATE TABLE IF NOT EXISTS `funcionario`(
  idFuncionario int not null,
  nome varchar(150) not null,
  cpf varchar(12) not null,
  endereco varchar(250) not null, 
  dataNascimento date not null,
  faixaSalarial int not null,

  PRIMARY KEY(idFuncionario),
  FOREIGN KEY(faixaSalarial) REFERENCES faixaSalarial(idFaixaSalarial)
);

CREATE TABLE IF NOT EXISTS `cliente`(
  idCliente int not null,
  cpfCliente varchar(12) not null,
  nomeCliente varchar(150) not null,
  dataNascimentoCliente date not null,

  PRIMARY KEY(idCliente)
);

CREATE TABLE IF NOT EXISTS `produto` (
  idProduto int not null,
  nomeProduto varchar(100) not null,
  descricaoProduto text not null,
  precoProduto decimal(4,2) not null,
  
  PRIMARY KEY(idProduto)
);

CREATE TABLE IF NOT EXISTS categoriaProduto (
  idCategoria int not null,
  nomeCategoria varchar(50) not null,
  idProduto int not null,

  PRIMARY KEY(idCategoria),
  FOREIGN KEY(idProduto) REFERENCES produto(idProduto)
);

CREATE TABLE IF NOT EXISTS funcionarioConta (
  email varchar(200) not null,
  senha varchar(150) not null,
  idFuncionario int not null,

  PRIMARY KEY(email),
  FOREIGN KEY(idFuncionario) REFERENCES funcionario(idFuncionario)
);


