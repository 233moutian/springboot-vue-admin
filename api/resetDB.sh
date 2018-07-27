#!/bin/bash
for i in $(find src/test/resources/dev/sql/*.sql) ; do
  mysql -uroot -p123456 admin_dev < ${i};
done
