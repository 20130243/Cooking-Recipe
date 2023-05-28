INSERT INTO recipes(title, description, serving, cooktime, image)
VALUES ('Mì udon xào cay', 'Mì udon xào với những nguyên liệu đơn giản sẽ là một lựa chọn vô cùng thích hợp cho bạn bởi hương vị thơm ngon và đậm đà của mì udon hòa quyện với sốt cay.', 1,  '15', 'https://example.com/image.jpg');

SET @recipe_id = LAST_INSERT_ID();

INSERT INTO ingredients(name, amount, unit, recipe_id)
VALUES ('Mì udon khô', 150, 'g', @recipe_id),
('Hành tây', 0.5, 'củ', @recipe_id),
('Tỏi', 2, 'tép', @recipe_id),
('Hành lá', 2, 'nhánh', @recipe_id),
('Dầu mè', 1, 'muỗng canh', @recipe_id),
('Nước tương', 1, 'muỗng canh', @recipe_id),
('Hạt nêm', 1, 'ít', @recipe_id),
('Ớt bột', 1, 'muỗng canh', @recipe_id),
('Dầu ăn', 2, 'muỗng canh', @recipe_id);

INSERT INTO steps(step_order, description, image, recipe_id)
VALUES (1, 'Làm sốt xào: cho vào chén 1 muỗng canh nước tương, 1 muỗng canh dầu mè, 1 muỗng canh ớt bột, 1/2 muỗng canh hạt nêm rồi trộn đều lên cho gia vị tan đều.', '', @recipe_id),
(2, 'Sơ chế hành tây và mì udon: hành tây cắt thành lát mỏng, mì udon chần sơ trong nước sôi khoảng 30 giây sau đó vớt ra rồi để ráo nước.', '', @recipe_id),
(3, 'Xào mì udon: bắc chảo lên bếp, cho vào chảo 2muỗng canh dầu ăn và đợi dầu nóng. Sau đó lần lượt cho 2 tép tỏi thái lát mỏng, hành tây đã sơ chế, hỗn hợp nước sốt đã khuấy đều, 2 nhánh hành lá băm nhỏ vào rồi đảo đều cho hành tây được mềm. Tiếp đó cho vào phần mì udon đã ráo nước, đảo đều hỗn hợp cho đến khi mì udon được tơi ra và các nguyên liệu được thấm vào mì. Nêm nếm gia vị cho vừa miệng rồi tắt bếp và thưởng thức.', '', @recipe_id);

SELECT 
  JSON_OBJECT(
    'id', recipes.id,
    'title', recipes.title,
    'description', recipes.description,
    'serving', recipes.serving,
    'cooktime', recipes.cooktime,
    'image', recipes.image,
    'ingredients', (
      SELECT CONCAT('[', GROUP_CONCAT(
        CONCAT(
          '{"name":"', ingredients.name,
          '","amount":"', ingredients.amount,
          '","unit":"', ingredients.unit,
          '"}'
        )
      ), ']')
      FROM ingredients
      WHERE ingredients.recipe_id = recipes.id
    ),
    'steps', (
      SELECT CONCAT('[', GROUP_CONCAT(
        CONCAT(
          '{step_order":', steps.step_order,
          ',"description":"', steps.description,
          '","image":"', steps.image,
          '"}'
        )
      ), ']')
      FROM steps
      WHERE steps.recipe_id = recipes.id
    ),
    'tag', (
	 	SELECT CONCAT('[', GROUP_CONCAT(
        CONCAT(
          '{"name":', tags.name,
			 '"}'
        )
      ), ']')
      FROM tags
      JOIN recipe_tag ON recipe_tag.tag_id =tags.id
      WHERE recipe_tag.recipe_id = recipes.id
	 )
  ) AS recipe_json
FROM recipes 

