create table base_table
(
  id           bigint auto_increment comment '主键'
    primary key,
  is_deleted   char collate utf8_bin              null comment '是否删除',
  gmt_create   datetime default CURRENT_TIMESTAMP null comment '创建时间',
  gmt_modified datetime                           null on update CURRENT_TIMESTAMP comment '修改时间',
  creator      varchar(32) collate utf8_bin       null comment '创建者',
  modifier     varchar(32) collate utf8_bin       null comment '修改者'
)
  comment 'base_table';
