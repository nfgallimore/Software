sqlite3 -batch "$HOME/Library/Application Support/Skype/$1/main.db" <<EOF
.mode csv
.output contacts.csv
select * from Contacts;
.output stdout
.exit
EOF 
