DROP SCHEMA IF EXISTS Base CASCADE;
CREATE SCHEMA Base;

CREATE TABLE Base.usuario(
	idUsuario int NOT NULL,
	nombreUsuario text NOT NULL,
	contraseña text NOT NULL,
	correo text NOT NULL,
	tipo text NOT NULL,
	CONSTRAINT id_Usuario_pkey PRIMARY KEY(idUsuario)
);

CREATE TABLE Base.tema(
	idTema int NOT NULL,
	idUsuario int NOT NULL,
	nombreTema text NOT NULL,
	CONSTRAINT id_tema_pkey PRIMARY KEY(idTema),
	CONSTRAINT fk_idUsuario FOREIGN KEY (idUsuario) REFERENCES Base.usuario(idUsuario)
);

CREATE TABLE Base.marcador(
	idMarcador int NOT NULL,
	idTema int NOT NULL,
	ubicación text NOT NULL,
	datos_utiles text NOT NULL,
	descripcion text NOT NULL,
	color int NOT NULL,
	CONSTRAINT id_Marcador_pkey PRIMARY KEY(idMarcador,idTema),
	CONSTRAINT fk_idTema FOREIGN KEY (idTema) REFERENCES Base.tema(idTema)
);

CREATE TABLE Base.comentario(
	idComentario int NOT NULL,
	idUsuario int not NULL,
	idMarcador int not NULL,
	idTema int not NULL,
	comentario text NOT NULL,
	fecha date,
	CONSTRAINT id_comentario_pkey PRIMARY KEY(idComentario,idMarcador,idTema),
	CONSTRAINT fk_idComeentario FOREIGN KEY(idMarcador,idTema) REFERENCES Base.marcador(idMarcador,idTema)
);
	
