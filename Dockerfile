FROM mysql:8

ENV MYSQL_ROOT_PASSWORD=Y1lmaz090909y
ENV MYSQL_DATABASE=smartera-management-system
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=Y1lmaz090909y

ADD schema.sql /docker-entrypoint-initdb.d
