#!/bin/bash

set -e

# Wait for MySQL to be ready
until mysqladmin ping -hlocalhost -uroot -p"Y1lmaz090909y" --silent; do
  echo "Waiting for MySQL to be ready..."
  sleep 5
done

# Set root password
mysql -uroot -p"Y1lmaz090909y" -e "ALTER USER 'root'@'localhost' IDENTIFIED BY 'Y1lmaz090909y';"

# Create new schema
mysql -uroot -p"Y1lmaz090909y" -e "CREATE DATABASE IF NOT EXISTS smartera-management-system;"

# Execute original entrypoint
exec "$@"
