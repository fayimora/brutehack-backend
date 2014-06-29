package models

import java.sql.Timestamp

/*  Input and Output fields are simply stubs for now */
case class Problem(id: Long,
                   createdAt: Timestamp,
                   updatedAt: Timestamp,
                   author: String,
                   title: String,
                   description: String,
                   hint: String,
                   input: String,
                   output: String)
