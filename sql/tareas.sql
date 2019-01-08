ALTER TABLE tareas DROP COLUMN username;
ALTER TABLE tareas ADD user_id int(10) AFTER fechalimite;
