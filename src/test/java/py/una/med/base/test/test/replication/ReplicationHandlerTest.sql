insert into ws_end_point (id, url, key, password, username, internal_tag) VALUES (1, '', '','','','');

insert into replication_info (number, id, ws_end_point_id, last_id, entity_class_name, dto_class_name, interval, active, last_sync, request_class_name, response_class_name) values (1, 1, 1, '0', 'py.una.med.base.test.test.replication.layers.ReplicatedEntity', 'py.una.med.base.test.test.replication.layers.ReplicatedEntity', '1', true, '2013-01-01', 'py.una.med.base.test.test.replication.layers.ReplicatedEntityRequest', 'py.una.med.base.test.test.replication.layers.ReplicatedEntityResponse');
--insert into replication_info (id, ws_end_point_id, last_id, entity_class_name, dto_class_name, interval, active, last_sync, request_class_name, response_class_name) values (2, 1, '0', 'py.una.med.base.test.test.replication.layers.ReplicatedEntityChild', 'py.una.med.base.test.test.replication.layers.ReplicatedEntityChild', '5', true, '2013-01-01', '', '');