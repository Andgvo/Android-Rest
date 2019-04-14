package com.example.androidclient.utilerias;

public abstract class DbTables {
    public static final String DATABASE_NAME= "proyecto2";
    //ENTIDAD USUSUARIO
    public static final String TABLE_USUARIO = "Usuario";
    public static final String USUARIO_ID = "idUsuario";
    public static final String USUARIO_NOMBRE = "nombreUsuario";
    public static final String USUARIO_APELLIDO = "apellidoUsuario";
    public static final String USUARIO_EMAIL = "emailUsuario";
    public static final String USUARIO_PASSWORD = "passwordUsuario";
    //ENTIDAD POST
    public static final String TABLE_POST = "Post";
    public static final String POST_ID = "idPost";
    public static final String POST_TITULO = "tituloPost";
    public static final String POST_CATEGORIA = "categoriaPost";
    public static final String POST_FECHA = "fechaPost";
    public static final String POST_RESUMEN = "resumenPost";
    public static final String POST_CONTENIDO = "contenidoPost";
    public static final String POST_URL = "urlImagenPost";
    public static final String POST_ID_USUARIO =  USUARIO_ID;
    //ENTIDAD COMENTARIO
    public static final String TABLE_COMENTARIO = "Comentario";
    public static final String COMENTARIO_ID = "idComentario";
    public static final String COMENTARIO_FECHA = "fechaComentario";
    public static final String COMENTARIO_CONTENIDO = "contenidoComentario";
    public static final String COMENTARIO_ID_USUARIO = USUARIO_ID;
    public static final String COMENTARIO_ID_POST = POST_ID;

    public static final String CREATE_TABLE_USUARIO =
            "create table IF NOT EXISTS Usuario("+
            "    idUsuario integer primary key,"+
            "	 nombreUsuario text not null,"+
            "    apellidoUsuario text not null,"+
            "    emailUsuario text not null,"+
            "    passwordUsuario text not null"+
             ");";
    public static final String CREATE_TABLE_POST =
            "create table IF NOT EXISTS Post("+
            "	 idPost integer not null primary key,"+
            "	 tituloPost text not null,"+
            "    categoriaPost text not null,"+
            "	 fechaPost DATETIME not null,"+
            "    resumenPost text not null,"+
            "    contenidoPost text not null,"+
            "    urlImagenPost text,"+
            "    idUsuario integer not null,"+
            "    foreign key(idUsuario) references Usuario(idUsuario) on update cascade on delete cascade"+
            ");";
    public static final String CREATE_TABLE_COMENTARIO =
            "create table IF NOT EXISTS Comentario("+
            "	idComentario integer not null primary key,"+
            "	fechaComentario DATETIME not null,"+
            "	contenidoComentario text not null,"+
            "   idUsuario integer not null,"+
            "   idPost integer not null,"+
            "	foreign key(idUsuario) references Usuario(idUsuario) on update cascade on delete cascade,"+
            "    foreign key(idPost) references Post(idPost) on update cascade on delete cascade"+
            ");";

    private static final String INSERT_USERS_TEST =
            "INSERT INTO Usuario VALUES(null, 'admin','','admin@gmail.com','1234'),"+
            "	(null, 'Andres','Lopez','andres@gmail.com','1234'),"+
            "    (null, 'Esteban','Montiel','esteban@gmail.com','1234'),"+
            "    (null, 'Aiko','Lopez','aiko@gmail.com','1234'),"+
            "    (null, 'Oscar','Barrera','oscar@gmail.com','1234');";

    private static final String INSERT_POST_TEST =
            "INSERT INTO Post VALUES"+
            "	(null, '¿Que es Java?', 'Java', '2019-03-28 11:00:00',"+
            "		'Java es un lenguaje de programación y una plataforma informática para el desarrollo de aplicaciones. Primero fue lanzado por Sun Microsystem en 1995 y más tarde adquirido por Oracle Corporation. Es uno de los lenguajes de programación más utilizados.', "+
            "		'Primero fue lanzado por Sun Microsystem en 1995 y más tarde adquirido por Oracle Corporation. Es uno de los lenguajes de programación más utilizados. La plataforma Java es una colección de programas que ayudan a desarrollar y ejecutar programas escritos en el lenguaje de programación Java. La plataforma Java incluye un motor de ejecución, un compilador y un conjunto de bibliotecas. JAVA es un lenguaje independiente de la plataforma. No es específico de ningún procesador o sistema operativo.', "+
            "		'assets/img/post1/1.jpg', 2 ),"+
            "	(null, '¿Qué es JVM?', 'Java', '2019-03-29 11:00:00',"+
            "		'JVM es un motor que proporciona un entorno de tiempo de ejecución para controlar el código o las aplicaciones de Java.', "+
            "		'Convierte bytecode de Java en lenguaje de máquina. JVM es una parte de JRE (Java Run Environment). Significa Java Virtual Machine. En otros lenguajes de programación, el compilador produce código de máquina para un sistema en particular. Sin embargo, el compilador Java produce código para una Máquina Virtual conocida como Máquina Virtual Java. En primer lugar, el código Java se cumple en bytecode. Este bytecode se interpreta en diferentes máquinas. Entre el sistema host y la fuente Java, Bytecode es un lenguaje intermedio. JVM es responsable de asignar espacio de memoria.', "+
            "		'assets/img/post2/1.jpg', 2 ),"+
            "	(null, '¿Qué es Abstracción en OOP?', 'Java', '2019-03-30 11:00:00',"+
            "		'La abstracción consiste en seleccionar datos de un conjunto más grande para mostrar solo los detalles relevantes del objeto.', "+
            "		'Ayuda a reducir la complejidad y el esfuerzo de programación. En Java, la abstracción se logra usando clases e interfaces abstractas. Es uno de los conceptos más importantes de OOPs. En un nivel superior, Abstracción es un proceso de ocultar los detalles de implementación y mostrar solo la funcionalidad para el usuario. Solo indica cosas importantes para el usuario y oculta los detalles internos, es decir. Mientras envía SMS, simplemente escriba el texto y envíe el mensaje. Aquí, no le importa el procesamiento interno de la entrega del mensaje. La abstracción se puede lograr utilizando la clase abstracta y el método abstracto en Java. ', "+
            "		'assets/img/post3/1.jpg', 2 ),"+
            "	(null, '¿Qué es la encapsulación en Java?', 'Java' , '2019-04-01 11:00:00',"+
            "		'La encapsulación es un principio de envolver datos (variables) y codificar juntos como una sola unidad. Es uno de los cuatro conceptos de OOP.', "+
            "		'Si un miembro de datos se declara “privado”, solo se puede acceder a él dentro de la misma clase. Ninguna clase externa puede acceder al miembro de datos de esa clase. Si necesita acceder a estas variables, debe usar los métodos públicos “getter” y “setter”. Los métodos de Getter y Setter se utilizan para crear, modificar, eliminar y ver los valores de las variables. La encapsulación vincula los datos con sus funcionalidades relacionadas. Aquí las funcionalidades significan “métodos” y los datos significan “variables”', "+
            "		'assets/img/post4/1.jpg', 3 ),"+
            "	(null, '¿Qué es herencia?', 'Java', '2019-04-02 11:00:00',"+
            "		'La herencia es un mecanismo en el que una clase adquiere la propiedad de otra clase. Por ejemplo, un niño hereda los rasgos de sus padres.', "+
            "		'Con la herencia, podemos reutilizar los campos y métodos de la clase existente. Por lo tanto, la herencia facilita la reutilización y es un concepto importante de OOP. Hay varios tipos de herencia en Java. Herencia única: En herencia única, una clase amplía otra clase (una sola clase). Herencia Múltiple: En Herencia Múltiple, una clase que extiende más de una clase. Java no es compatible con herencia múltiple. Herencia multinivel: En la herencia multinivel, una clase puede heredar de una clase derivada. Por lo tanto, la clase derivada se convierte en la clase base para la nueva clase. Herencia jerárquica: En Herencia jerárquica, una clase es heredada por muchas subclases. Herencia Híbrida: La herencia híbrida es una combinación de herencia única y múltiple.', "+
            "		'assets/img/post5/1.jpg', 3 ),"+
            "	(null, '¿Qué es polimorfismo?', 'Java', '2019-04-03 11:00:00',"+
            "		'El polimorfismo es un concepto OOPs donde un nombre puede tener muchas formas.', "+
            "		'Por ejemplo, tiene un teléfono inteligente para comunicarse. El modo de comunicación que elijas podría ser cualquier cosa. Puede ser una llamada, un mensaje de texto, un mensaje de foto, correo, etc. Por lo tanto, el objetivo es común que sea la comunicación, pero su enfoque es diferente. Esto se llama polimorfismo.', "+
            "		'assets/img/post6/1.jpg', 3 );";

    private static final String INSERT_COMENTARIO_TEST =
            "INSERT INTO Comentario VALUES( null, now(), 'Buen tutoria para inicia en Java', 3, 1 ),"+
            "		( null, now(), 'Excelente inicio en Java :D', 4, 1 ),"+
            "		( null, now(), 'Super cool, ya comienzo a entender', 4, 2 ),"+
            "		( null, now(), 'Nunca había entendido eso XD', 4, 2 ),"+
            "		( null, now(), 'Genial, muchas gracias', 4, 3 ),"+
            "		( null, now(), 'Creo que lo pudiste haber explciado un poco mejor', 4, 3 );";
}