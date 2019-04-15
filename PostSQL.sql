drop database if exists proyecto2;
create database proyecto2;
use proyecto2;

create table Usuario(
	idUsuario int auto_increment primary key,
	nombreUsuario varchar(30) not null,
    apellidoUsuario varchar(30) not null,
    emailUsuario varchar(30) not null,
    passwordUsuario varchar(30) not null
);

create table Post(
	idPost int not null auto_increment primary key,
	tituloPost varchar(100) not null,
    categoriaPost varchar(30) not null,
	fechaPost datetime not null,
    resumenPost varchar(1000) not null,
    contenidoPost varchar(2000) not null,
    urlImagenPost varchar(100),
    idUsuario int not null,
    foreign key(idUsuario) references Usuario(idUsuario) on update cascade on delete cascade
);

create table Comentario(
	idComentario int not null auto_increment primary key,
	fechaComentario datetime not null,
	contenidoComentario varchar(1000) not null,
    idUsuario int not null,
    idPost int not null,
	foreign key(idUsuario) references Usuario(idUsuario) on update cascade on delete cascade,
    foreign key(idPost) references Post(idPost) on update cascade on delete cascade
);

DELIMITER $$
################## PROCEDIMIENTOS PARA USUARIO ##################################
CREATE PROCEDURE spInsertUsuario( nombre varchar(30), apellido varchar(30), correo varchar(30), passwordU varchar(30))
begin
	INSERT INTO Usuario VALUES(null, nombre, apellido, correo, passwordU);
end $$

CREATE PROCEDURE spUpdateUsuario(id int, nombre varchar(30), apellido varchar(30), correo varchar(30), passwordU varchar(30))
begin
	UPDATE Usuario SET nombreUsuario = nombre, apellidoUsuario = apellido, emailUsuario=correo, passwordUsuario = passwordU WHERE idUsuario=id;
end $$

CREATE PROCEDURE spDeleteUsuario( id int )
begin
	DELETE FROM Usuario WHERE idUsuario=id;
end $$

CREATE PROCEDURE spSelectUsuario( boleta bigint )
begin
	SELECT * FROM Usuario WHERE idUsuario=id;
#    SELECT a.noBoleta, a.nombreUsuario, a.paternoUsuario, a.maternoUsuario, a.email, a.idCarrera, c.nombreCarrera from Usuario a, Carrera c 
#	WHERE a.noBoleta = boleta AND a.idCarrera = c.idCarrera;
end $$

CREATE PROCEDURE spSelectAllUsuario()
begin
	SELECT * FROM Usuario;
end $$

CREATE PROCEDURE spLoginUsuario(nombre char(30), passwordU char(30) )
begin
	SELECT * FROM Usuario WHERE nombreUsuario = nombre AND passwordUsuario = passwordU;
end $$

###################### SP PARA POST #####################
    
CREATE PROCEDURE spInsertPost( titulo varchar(100), categoria varchar(30), fecha date, resumenPost varchar(1000), contenidoPost varchar(2000), urlImagenPost varchar(80), idUser int)
begin
	INSERT INTO Post VALUES(null, titulo, categoria, fecha, resumenPost, contenidoPost, urlImagenPost, idUser);
end $$

CREATE PROCEDURE spUpdatePost( id int, titulo varchar(100), categoria varchar(30), fecha date, resumen varchar(1000), contenido varchar(2000), imagen varchar(80), idUser int)
begin
	UPDATE Post SET tituloPost = titulo, categoriaPost=categoria, fechaPost = fecha, resumenPost = resumen, contenidoPost=contenido, urlImagenPost = imagen WHERE idPost=id AND idUsuario=idUser;
end $$

CREATE PROCEDURE spDeletePost( id int )
begin
	DELETE FROM Post WHERE idPost=id;
end $$

CREATE PROCEDURE spSelectPost( id int )
begin
	SELECT p.idPost, p.tituloPost, p.categoriaPost, p.fechaPost, p.resumenPost, p.contenidoPost, p.urlImagenPost, u.idUsuario, u.nombreUsuario, u.apellidoUsuario, u.emailUsuario 
	FROM Post p, Usuario u WHERE  p.idUsuario = u.idUsuario AND p.idPost = id;
end $$

CREATE PROCEDURE spSelectPostCategoria( categoria varchar(30) )
begin
	SELECT p.idPost, p.tituloPost, p.categoriaPost, p.fechaPost, p.resumenPost, p.contenidoPost, p.urlImagenPost, u.idUsuario, u.nombreUsuario, u.apellidoUsuario, u.emailUsuario 
	FROM Post p, Usuario u WHERE  p.idUsuario = u.idUsuario AND p.categoriaPost = categoria;
end $$

CREATE PROCEDURE spSelectPostMes( fecha date )
begin
	SELECT p.idPost, p.tituloPost, p.categoriaPost, p.fechaPost, p.resumenPost, p.contenidoPost, p.urlImagenPost, u.idUsuario, u.nombreUsuario, u.apellidoUsuario, u.emailUsuario 
	FROM Post p, Usuario u WHERE  p.idUsuario = u.idUsuario AND MONTH(p.fechaPost)=MONTH(fecha);
end $$

CREATE PROCEDURE spSelectPostUsuario( idUser int )
begin
	SELECT p.idPost, p.tituloPost, p.categoriaPost, p.fechaPost, p.resumenPost, p.contenidoPost, p.urlImagenPost, u.idUsuario, u.nombreUsuario, u.apellidoUsuario, u.emailUsuario 
	FROM Post p, Usuario u WHERE  p.idUsuario = u.idUsuario AND p.idUsuario = idUser;
end $$

CREATE PROCEDURE spSelectAllPost()
begin
	SELECT p.idPost, p.tituloPost, p.categoriaPost, p.fechaPost, p.resumenPost, p.contenidoPost, p.urlImagenPost, u.idUsuario, u.nombreUsuario, u.apellidoUsuario, u.emailUsuario 
	FROM Post p, Usuario u WHERE  p.idUsuario = u.idUsuario;
end $$

###################################### PROCEDURES PARA COMENTARIO ##############################

CREATE PROCEDURE spInsertComentario( fecha date, contenidoComentario varchar(1000), idUser int, idPost int)
begin
	INSERT INTO Comentario VALUES(null, fecha, contenidoComentario, idUser, idPost);
end $$

CREATE PROCEDURE spUpdateComentario( id int, fecha date, contenido varchar(1000), idU int, idP int)
begin
	UPDATE Comentario SET fechaComentario = fecha, contenidoComentario=contenido WHERE idComentario=id AND idUsuario=idU AND idPost= idP;
end $$

CREATE PROCEDURE spDeleteComentario( id int, idU int, idP int )
begin
	DELETE FROM Comentario WHERE idComentario=id AND idUsuario=idU AND idPost = idP;
end $$

CREATE PROCEDURE spSelectComentario( id int, idU int, idP int )
begin
	SELECT c.idComentario, c.fechaComentario, c.contenidoComentario, u.idUsuario, u.nombreUsuario, p.idPost
	FROM Comentario c, Usuario u, Post p WHERE c.idPost = id AND u.idUsuario=idU AND p.idPost=idP AND c.idPost = p.idPost AND c.idUsuario = u.idUsuario;
end $$

CREATE PROCEDURE spSelectAllComentario(idP int)
begin
	SELECT c.idComentario, c.fechaComentario, c.contenidoComentario, u.idUsuario, u.nombreUsuario, p.idPost
	FROM Comentario c, Usuario u, Post p WHERE c.idPost = idP AND c.idPost = p.idPost AND c.idUsuario = u.idUsuario;
end $$

################################################
DELIMITER ;

################## TEST VALUES ############################################


SELECT * FROM Usuario;
SELECT * FROM Post;
SELECT * FROM Comentario;

/*
INSERT INTO Usuario VALUES(null, 'admin','','admin@gmail.com','1234'),
	(null, 'Andres','Lopez','andres@gmail.com','1234'),
    (null, 'Esteban','Montiel','esteban@gmail.com','1234'),
    (null, 'Aiko','Lopez','aiko@gmail.com','1234'),
    (null, 'Oscar','Barrera','oscar@gmail.com','1234');

INSERT INTO Post VALUES
	(null, '¿Que es Java?', 'Java', '2019-03-28 11:00:00',
		'Java es un lenguaje de programación y una plataforma informática para el desarrollo de aplicaciones. Primero fue lanzado por Sun Microsystem en 1995 y más tarde adquirido por Oracle Corporation. Es uno de los lenguajes de programación más utilizados.', 
		'Primero fue lanzado por Sun Microsystem en 1995 y más tarde adquirido por Oracle Corporation. Es uno de los lenguajes de programación más utilizados. La plataforma Java es una colección de programas que ayudan a desarrollar y ejecutar programas escritos en el lenguaje de programación Java. La plataforma Java incluye un motor de ejecución, un compilador y un conjunto de bibliotecas. JAVA es un lenguaje independiente de la plataforma. No es específico de ningún procesador o sistema operativo.', 
		'assets/img/post1/1.jpg', 2 ),
	(null, '¿Qué es JVM?', 'Java', '2019-03-29 11:00:00',
		'JVM es un motor que proporciona un entorno de tiempo de ejecución para controlar el código o las aplicaciones de Java.', 
		'Convierte bytecode de Java en lenguaje de máquina. JVM es una parte de JRE (Java Run Environment). Significa Java Virtual Machine. En otros lenguajes de programación, el compilador produce código de máquina para un sistema en particular. Sin embargo, el compilador Java produce código para una Máquina Virtual conocida como Máquina Virtual Java. En primer lugar, el código Java se cumple en bytecode. Este bytecode se interpreta en diferentes máquinas. Entre el sistema host y la fuente Java, Bytecode es un lenguaje intermedio. JVM es responsable de asignar espacio de memoria.', 
		'assets/img/post2/1.jpg', 2 ),
	(null, '¿Qué es Abstracción en OOP?', 'Java', '2019-03-30 11:00:00',
		'La abstracción consiste en seleccionar datos de un conjunto más grande para mostrar solo los detalles relevantes del objeto.', 
		'Ayuda a reducir la complejidad y el esfuerzo de programación. En Java, la abstracción se logra usando clases e interfaces abstractas. Es uno de los conceptos más importantes de OOPs. En un nivel superior, Abstracción es un proceso de ocultar los detalles de implementación y mostrar solo la funcionalidad para el usuario. Solo indica cosas importantes para el usuario y oculta los detalles internos, es decir. Mientras envía SMS, simplemente escriba el texto y envíe el mensaje. Aquí, no le importa el procesamiento interno de la entrega del mensaje. La abstracción se puede lograr utilizando la clase abstracta y el método abstracto en Java. ', 
		'assets/img/post3/1.jpg', 2 ),
	(null, '¿Qué es la encapsulación en Java?', 'Java' , '2019-04-01 11:00:00',
		'La encapsulación es un principio de envolver datos (variables) y codificar juntos como una sola unidad. Es uno de los cuatro conceptos de OOP.', 
		'Si un miembro de datos se declara “privado”, solo se puede acceder a él dentro de la misma clase. Ninguna clase externa puede acceder al miembro de datos de esa clase. Si necesita acceder a estas variables, debe usar los métodos públicos “getter” y “setter”. Los métodos de Getter y Setter se utilizan para crear, modificar, eliminar y ver los valores de las variables. La encapsulación vincula los datos con sus funcionalidades relacionadas. Aquí las funcionalidades significan “métodos” y los datos significan “variables”', 
		'assets/img/post4/1.jpg', 3 ),
	(null, '¿Qué es herencia?', 'Java', '2019-04-02 11:00:00',
		'La herencia es un mecanismo en el que una clase adquiere la propiedad de otra clase. Por ejemplo, un niño hereda los rasgos de sus padres.', 
		'Con la herencia, podemos reutilizar los campos y métodos de la clase existente. Por lo tanto, la herencia facilita la reutilización y es un concepto importante de OOP. Hay varios tipos de herencia en Java. Herencia única: En herencia única, una clase amplía otra clase (una sola clase). Herencia Múltiple: En Herencia Múltiple, una clase que extiende más de una clase. Java no es compatible con herencia múltiple. Herencia multinivel: En la herencia multinivel, una clase puede heredar de una clase derivada. Por lo tanto, la clase derivada se convierte en la clase base para la nueva clase. Herencia jerárquica: En Herencia jerárquica, una clase es heredada por muchas subclases. Herencia Híbrida: La herencia híbrida es una combinación de herencia única y múltiple.', 
		'assets/img/post5/1.jpg', 3 ),
	(null, '¿Qué es polimorfismo?', 'Java', '2019-04-03 11:00:00',
		'El polimorfismo es un concepto OOPs donde un nombre puede tener muchas formas.', 
		'Por ejemplo, tiene un teléfono inteligente para comunicarse. El modo de comunicación que elijas podría ser cualquier cosa. Puede ser una llamada, un mensaje de texto, un mensaje de foto, correo, etc. Por lo tanto, el objetivo es común que sea la comunicación, pero su enfoque es diferente. Esto se llama polimorfismo.', 
		'assets/img/post6/1.jpg', 3 );

INSERT INTO Comentario VALUES( null, now(), 'Buen tutoria para inicia en Java', 3, 1 ),
		( null, now(), 'Excelente inicio en Java :D', 4, 1 ),
		( null, now(), 'Super cool, ya comienzo a entender', 4, 2 ),
		( null, now(), 'Nunca había entendido eso XD', 4, 2 ),
		( null, now(), 'Genial, muchas gracias', 4, 3 ),
		( null, now(), 'Creo que lo pudiste haber explciado un poco mejor', 4, 3 );
*/