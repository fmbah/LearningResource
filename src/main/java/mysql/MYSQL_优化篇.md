explain extended select * from trial.zhubo_video as zv left join trial.zhubo_user_info_detail as zuid 
on zv.author_user_id = zuid.platform_user_id;
show warnings;

select `trial`.`zv`.`id` AS `id`,`trial`.`zv`.`pk_id` AS `pk_id`,
`trial`.`zv`.`platform` AS `platform`,`trial`.`zv`.`aweme_id` AS `aweme_id`,
`trial`.`zv`.`author_name` AS `author_name`,
`trial`.`zv`.`author_user_id` AS `author_user_id`,
`trial`.`zv`.`sec_uid` AS `sec_uid`,
`trial`.`zv`.`cover_thumbnail_urls` AS `cover_thumbnail_urls`,
`trial`.`zv`.`mv_url` AS `mv_url`,`trial`.`zv`.`pubdate` AS `pubdate`,`trial`.`zv`.`group_id` AS `group_id`,`trial`.`zv`.`is_top` AS `is_top`,`trial`.`zv`.`rate` AS `rate`,`trial`.`zv`.`title` AS `title`,
`trial`.`zv`.`play_count` AS `play_count`,`trial`.`zv`.`like_count` AS `like_count`,
`trial`.`zv`.`comment_count` AS `comment_count`,`trial`.`zv`.`forward_count` AS `forward_count`,`trial`.`zv`.`share_count` AS `share_count`,`trial`.`zv`.`share_url` AS `share_url`,`trial`.`zv`.`create_at` AS `create_at`,`trial`.`zv`.`update_at` AS `update_at`,`trial`.`zv`.`short_id` AS `short_id`,`trial`.`zuid`.`id` AS `id`,`trial`.`zuid`.`user_no` AS `user_no`,`trial`.`zuid`.`created_at` AS `created_at`,`trial`.`zuid`.`updated_at` AS `updated_at`,`trial`.`zuid`.`like_num` AS `like_num`,`trial`.`zuid`.`ad_price` AS `ad_price`,`trial`.`zuid`.`head_address` AS `head_address`,`trial`.`zuid`.`qr_address` AS `qr_address`,`trial`.`zuid`.`cs_channel_id` AS `cs_channel_id`,`trial`.`zuid`.`medium_user_id` AS `medium_user_id`,`trial`.`zuid`.`fans_num` AS `fans_num`,`trial`.`zuid`.`platform` AS `platform`,`trial`.`zuid`.`platform_user_id` AS `platform_user_id`,`trial`.`zuid`.`status` AS `status`,`trial`.`zuid`.`nick` AS `nick`,`trial`.`zuid`.`media_type` AS `media_type`,
`trial`.`zuid`.`adv_status` AS `adv_status` 
from `trial`.`zhubo_video` `zv` left join `trial`.`zhubo_user_info_detail` `zuid`
 on((`trial`.`zv`.`author_user_id` = convert(`trial`.`zuid`.`platform_user_id` using utf8mb4))) where 1
 
 两表 链接条件类型/字符集不一致  导致索引失效
 
 
 
 
