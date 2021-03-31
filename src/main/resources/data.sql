INSERT INTO TOOL(title, link, description) VALUES('Notion', 'https://notion.so', 'All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized.');

INSERT INTO TAG(name) VALUES('web');
INSERT INTO TAG(name) VALUES('framework');
INSERT INTO TAG(name) VALUES('node');
INSERT INTO TAG(name) VALUES('api');
INSERT INTO TAG(name) VALUES('json');

INSERT INTO TOOL_TAGS(tool_id, tags_id) VALUES(1, 1);
INSERT INTO TOOL_TAGS(tool_id, tags_id) VALUES(1, 3);
INSERT INTO TOOL_TAGS(tool_id, tags_id) VALUES(1, 5);