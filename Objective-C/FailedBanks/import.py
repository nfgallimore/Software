def mysplit (string):
	quote = False
	retval = []
	current = ""
	for char in string:
		if char == '"':
			quote = not quote
		elif char == ',' and not quote:
			retval.append(current)
			current = ""
		else:
			current += char
	retval.append(current)
	return retval
	
import datetime
# Read lines form file, skipping first line
data = open("banklist.csv", "r").readlines()[1:]
for entry in data:
	# Parse values
	vals = mysplit(entry.strip())
	# Convert dates to sqlite3 standard format
	vals[4] = datetime.datetime.strptime(vals[4], "%d-%b-%y")
	vals[5] = datetime.datetime.strptime(vals[5], "%d-%b-%y")
	# Insert the row
	print "Inserting %s..." % (vals[0])
	sql = "insert into failed_banks values(NULL, ?, ?, ?, ?, ?, ?)"
	c.execute(sql, vals)

# Done
conn.commit()
