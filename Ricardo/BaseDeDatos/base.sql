DROP SCHEMA IF EXISTS Base CASCADE;
CREATE SCHEMA Base;
CREATE TABLE Base.usuario(
	idUsuario serial primary key,
	nombreUsuario text NOT NULL,
	contraseña text NOT NULL,
	correo text NOT NULL,
	tipo text NOT NULL,
	CONSTRAINT correo unique(correo),
	CONSTRAINT nombreU unique(nombreUsuario)
);

CREATE TABLE Base.tema(
	idTema Serial primary key,
	idUsuario integer NOT NULL,
	nombreTema text NOT NULL,
	CONSTRAINT fk_idUsuario FOREIGN KEY (idUsuario) REFERENCES Base.usuario(idUsuario) ON DELETE CASCADE,
	CONSTRAINT nombre unique(nombreTema)
);

CREATE TABLE Base.marcador(
	idMarcador Serial primary key,
	idTema integer NOT NULL,
	latitud FLOAT NOT NULL,
	longitud FLOAT NOT NULL,
	datos_utiles text NOT NULL,
	descripcion text NOT NULL,
	color int NOT NULL,
	CONSTRAINT id_Marcador_pkey unique(idMarcador,idTema),
	CONSTRAINT fk_idTema FOREIGN KEY (idTema) REFERENCES Base.tema(idTema) ON DELETE CASCADE
);

CREATE TABLE Base.comentario(
	idComentario serial primary key,
	idUsuario integer not NULL,
	idMarcador integer not NULL,
	idTema integer not NULL,
	comentario text NOT NULL,
	fecha date,
	CONSTRAINT id_comentario_pkey unique(idComentario,idMarcador,idTema),
	CONSTRAINT fk_idComeentario FOREIGN KEY(idMarcador,idTema) REFERENCES Base.marcador(idMarcador,idTema) ON DELETE CASCADE
);
	
