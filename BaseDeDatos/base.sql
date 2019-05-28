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
    return new;
  end;
$$ language plpgsql;

drop trigger if exists hashpassword on Base.usuario;

create trigger hashpassword
before insert on Base.usuario
for each row execute procedure Base.hash();

drop function if exists Base.obten_usuario;

create or replace function Base.obten_usuario(correo text, contraseña text) returns Base.usuario as $$
  select *
  from Base.usuario
  where correo = correo and contrasena = crypt(contraseña, contrasena)
$$ language sql stable;



CREATE TABLE Base.color (
  id serial primary key,
  nombre text not null,
  hex_color text unique not null,
  constraint is_hex_color check (hex_color ~* '^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$')
);

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
	CONSTRAINT id_comentario_pkey unique(idComentario,idMarcador,idTema),
	CONSTRAINT fk_idComeentario FOREIGN KEY(idMarcador,idTema) REFERENCES Base.marcador(idMarcador,idTema) ON DELETE CASCADE
);
insert into Base.usuario(nombreUsuario,contrasena,correo,tipo,activo) values('Shirifcam','contraseñña','shirifcam@gmail.com','0',true);

	
insert into Base.color (nombre, hex_color)
values ('black', '#000000'),
       ('red', '#FF0000'),
       ('lime', '#00FF00'),
       ('blue', '#0000FF'),
       ('green', '#008000');

insert into Base.tema(idUsuario,color,nombreTema) values(1,'azul','Hospital');
insert into base.marcador(idTema,latitud,longitud,datos_utiles,descripcion) values (1,19.322930,-99.221742,'Complicado','Meh');
