# Projeto de Programação N04 - Grupo 01

LINK: https://youtu.be/azgJe1s1lS0

## 🎯 Objetivo do módulo desenvolvido
> O objetivo é criar uma plataforma responsável por cadastrar eventos e pessoas, guardando suas informações e realizando ligações entre ambos.

---

## 📚 Bibliotecas utilizadas
> - Spring Boot  
> - JPA  
> - MySQL  
> - JavaFX  
> - Docker  

---

## ⚙️ Instruções de execução

1. Tenha o **Docker** instalado.  
   👉 [Download Docker](https://www.docker.com/)

2. Na **raiz do projeto**, execute:
   ```bash
   docker-compose up --build

3. Para visualizar o db no terminal, execute:
   ```bash
   docker exec -it projeto-db mysql -u root -p
4. Depois:
   ```sql
   SHOW DATABASES;
   USE GRUPO01banco;
   SHOW TABLES;


## Responsabilidades de cada integrante:  

> **Backend:**  
>> **Classes:** João V Argolo e Pedro Faria  
>> **Controllers:** Alan Rocha  
>> **Services:** Luiz Fernando  
>> **Repository:** Andrey Felipe, Pedro Faria e Alan Rocha  

> **Frontend:**  
>> **Arthur de Oliveira**


## Exemplos de Saída:

Saida do select na tabela do espectador:
```bash
+---------------+-------------+------------+------------------------+-------------------+---------+------------------+-------------+---------+
| id_espectador | cpf         | data_nasc  | email                   | nome             | perfil  | senha           | telefone    | status  |       
+---------------+-------------+------------+------------------------+------------------+---------+------------------+-------------+---------+       
|             1 | 11122233344 | 2001-07-10 | ana@example.com         | Ana              | sokdoef | 1234            | 79998057227 | INATIVO |       
|             2 | 11122233354 | 2025-11-03 | chups@gmail.com         | chupetinha       | chupestinks | AmoAlan01!  | NULL        | INATIVO |       
|             3 | 11111111111 | 2001-07-11 | cuscuz@example.com      | Luciano Hulk     | enpap  | Shimerbiulock1!  | 79998057227 | INATIVO |       
|             4 | 12345678910 | 2025-11-13 | chessvideos@example.com | Ananias Ferreira | ananias | Xablau01!!!     | NULL        | INATIVO |       
+---------------+-------------+------------+------------------------+-------------------+---------+------------------+-------------+---------+
