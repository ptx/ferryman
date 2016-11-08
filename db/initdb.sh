#!/bin/sh

set -e

# Alter User password as superuser
export PGUSER="postgres"

psql <<-EOSQL
ALTER USER $POSTGRES_USER WITH PASSWORD '$POSTGRES_PASSWORD';
EOSQL

# change to new user
export PGUSER=$POSTGRES_USER

# create ferryman schema
psql -d $POSTGRES_DB -f /tmp/ferryman.sql
