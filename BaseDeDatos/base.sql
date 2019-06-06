drop extension if exists pgcrypto;
create extension pgcrypto;


DROP SCHEMA IF EXISTS Base CASCADE;
CREATE SCHEMA Base;


CREATE TABLE Base.usuario(
	idUsuario serial primary key,
	nombreUsuario text NOT NULL,
	contrasena text NOT NULL,
	correo text NOT NULL,
	tipo text NOT NULL,
	fotografia bytea,
  	activo boolean not null default false,
  	activacion char(32) not null,
	CONSTRAINT correo unique(correo),
	CONSTRAINT nombreU unique(nombreUsuario)
);


drop function if exists Base.hash();

create or replace function Base.hash() returns trigger as $$
  begin
    if TG_OP = 'INSERT' then
       if new.activacion is null then
         new.activacion = MD5(new.nombreUsuario || new.contrasena || new.correo);
       end if;
       new.contrasena = crypt(new.contrasena, gen_salt('bf', 8)::text);
    end if;
    if TG_OP = 'UPDATE' then
       if not old.contrasena = new.contrasena then
         new.contrasena = crypt(new.contrasena, gen_salt('bf', 8)::text);
        end if;
    end if;
    return new;
  end;
$$ language plpgsql;

drop function if exists Base.hashU();

drop trigger if exists hashpassword on Base.usuario;

create trigger hashpassword
before insert on Base.usuario
for each row execute procedure Base.hash();

drop trigger if exists hashpasswordU on Base.usuario;

create trigger hashpasswordU
before update on Base.usuario
for each row execute procedure Base.hash();


drop function if exists Base.obten_usuario;

create or replace function Base.obten_usuario(correo text, contraseña text) returns Base.usuario as $$
  select *
  from (select * from Base.usuario where contrasena = crypt(contraseña, contrasena)) as usu
  where usu.correo = correo
$$ language sql stable;

CREATE TABLE Base.tema(
	idTema serial primary key,
	idUsuario integer NOT NULL,
	color text NOT NULL,
	nombreTema text NOT NULL,
	CONSTRAINT fk_idUsuario FOREIGN KEY (idUsuario) REFERENCES Base.usuario(idUsuario) ON DELETE CASCADE,
	CONSTRAINT nombre unique(nombreTema)
);

CREATE TABLE Base.marcador(
	idMarcador serial primary key,
	idTema integer NOT NULL,
	latitud FLOAT NOT NULL,
	longitud FLOAT NOT NULL,
	datos_utiles text NOT NULL,
	descripcion text NOT NULL,
	CONSTRAINT id_Marcador_pkey unique(idMarcador,idTema),
	CONSTRAINT fk_idTema FOREIGN KEY (idTema) REFERENCES Base.tema(idTema) ON DELETE CASCADE
);

CREATE TABLE Base.comentario(
	idComentario serial primary key,
	idUsuario integer not NULL,
	idMarcador integer not NULL,
	idTema integer not NULL,
	comentario text NOT NULL,
	fecha text,
	gusta integer,
	nogusta integer,
	total integer,
	CONSTRAINT id_comentario_pkey unique(idComentario,idMarcador,idTema),
	CONSTRAINT fk_idComeentario FOREIGN KEY(idMarcador,idTema) REFERENCES Base.marcador(idMarcador,idTema) ON DELETE CASCADE
);

CREATE TABLE Base.calificacion(
	idCalificacion serial primary Key,
	idUsuario integer not NULL,
	idComentario integer not NULL,
	eleccion boolean,
	CONSTRAINT id_calificar_pkey unique (idCalificacion,idUsuario,idComentario),
	CONSTRAINT fk_idCalificacion  FOREIGN KEY(idComentario) REFERENCES
Base.comentario(idComentario) ON DELETE CASCADE,
	CONSTRAINT fk1_idCalificacion FOREIGN KEY(idUsuario) REFERENCES
Base.usuario(idUsuario) ON DELETE CASCADE
);

insert into Base.usuario(nombreUsuario,contrasena,correo,tipo,activo) values('Shirifcam','contraseña12','shirifcam@gmail.com','0',true);

insert into Base.usuario(nombreUsuario,contrasena,correo,tipo,activo) values('PaperDragon','papel','paperdragon999@gmail.com','0',true);

insert into Base.usuario(nombreUsuario,contrasena,correo,tipo,activo) values('Ricardo','ancaria12','r.badillo12@ciencias.unam.mx','1',true);

insert into Base.usuario(nombreUsuario,contrasena,correo,tipo,activo) values('Carlos','salcedo','carlossr@ciencias.unam.mx','1',true);

insert into Base.usuario(nombreUsuario,contrasena,correo,tipo,activo) values('Mau','furro','mausuarez@ciencias.unam.mx','2',true);

insert into Base.usuario(nombreUsuario,contrasena,correo,tipo,activo) values('Edith','fuerza','edithareyesl@ciencias.unam.mx','2',true);

insert into Base.tema(idUsuario,color,nombreTema) values(5,'ff0000','HOSPITAL');
insert into Base.tema(idUsuario,color,nombreTema) values(6,'7f31e6','CINES');
insert into base.marcador(idTema,latitud,longitud,datos_utiles,descripcion) values (1,19.322930,-99.221742,'Hospital','Muy Necesario');
insert into base.marcador(idTema,latitud,longitud,datos_utiles,descripcion) values (1,19.3124393749943,-99.221134185791,'Hospital Angeles del Pedregal','Muy buen servicio');
insert into base.marcador(idTema,latitud,longitud,datos_utiles,descripcion) values (2,19.3061365356115,-99.165089824534,'Cines GranSur','Es un cinepolis');

insert into Base.comentario(idUsuario,idMarcador,idTema,comentario,fecha,gusta,nogusta,total) values
(4,2,1,'Que buen hospital, muy buen servicio','06/06/2019',2,0,2);

insert into Base.comentario(idUsuario,idMarcador,idTema,comentario,fecha,gusta,nogusta,total) values
(3,2,1,'Excelente trato a sus pacientes','06/06/2019',1,0,1);

insert into Base.calificacion(idUsuario,idComentario,eleccion) values
(4,1,TRUE);

insert into Base.calificacion(idUsuario,idComentario,eleccion) values
(3,1,TRUE);

insert into Base.calificacion(idUsuario,idComentario,eleccion) values
(3,2,TRUE);

