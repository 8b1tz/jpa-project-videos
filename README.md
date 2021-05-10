<h1> Projeto 3 de Persistência de Objetos 2020.2</h1>

<h2>
Alunas:  <br> 
  - Yohanna de Oliveira Cavalcanti <br> 
  - Ana Julia Oliveira Lins

</h2>

Implementação:<br>
• Implementar o primeiro projeto para usar um banco relacional:<br>
o Mapear as classes do modelo de negócio com JPA<br>
o Usar a mesma Fachada/Aplicações do projeto1<br>
o Usar a classe DAO genérico do exemplo AgendaJPA-dao<br>
o Usar tecnologia Maven (pom.xml) <br>
o Configurar ip=54.94.169.84 e database=projeto3. Mapear as tabelas com nome<br>
concatenado com a matrícula (ex: tabela Video20192387654)<br>
o Adicionar o atributo idade na classe Visualizacao que será calculado nos<br>
eventos (trigger) @PrePersist e @PostLoad, utilizando a data do computador<br>
o Adicionar o atributo versao em cada classe, para permitir o controle de<br>
concorrência otimista entre instâncias da mesma aplicação<br>
o Desativar o cache de objetos, caso necessário<br>
