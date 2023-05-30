package com.example.cookingrecipe.Domain.Network;

import com.example.cookingrecipe.Domain.Model.Ingredient;
import com.example.cookingrecipe.Domain.Model.Recipe;
import com.example.cookingrecipe.Domain.Model.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    List<Ingredient> ingredients;
    List<Step> steps;

    public List<Recipe> recipeList() {
        List<Recipe> list = new ArrayList<>();

        list.add(recipe2());

        list.add(recipe3());

        list.add(recipe4());


        list.add(recipe5());

        list.add(recipe6());

        list.add(recipe7());

        list.add(recipe8());

        list.add(recipe9());

        list.add(recipe10());

        list.add(recipe11());

        list.add(recipe12());

        list.add(recipe13());

        list.add(recipe14());

        list.add(recipe15());

        list.add(recipe16());

        list.add(recipe17());

        list.add(recipe18());
        list.add(recipe19());
        return list;
    }

    private Recipe recipe2() {
        Ingredient gaoNep = new Ingredient("Gạo nếp", "500", "g");
        Ingredient duiGaGocTu = new Ingredient("Đùi gà góc tư", "1", "cái");
        Ingredient hatSenKho = new Ingredient("Hạt sen khô", "20", "g");
        Ingredient laSenTuoi = new Ingredient("Lá sen tươi", "1", "lá");
        Ingredient saBam = new Ingredient("Sả băm", "1", "muỗng canh");
        Ingredient hanhBam = new Ingredient("Hành băm", "1", "muỗng cà phê");
        Ingredient toiXay = new Ingredient("Tỏi xay", "1", "muỗng cà phê");
        Ingredient hanhLa = new Ingredient("Hành lá", "1", "nhánh");
        Ingredient hanhPhi = new Ingredient("Hành Phi", "1", "muỗng canh");
        Ingredient nuocTuong = new Ingredient("Nước tương", "2", "muỗng cà phê");
        Ingredient muoi = new Ingredient("Muối", "1/2", "muỗng cà phê");
        Ingredient duong = new Ingredient("Đường", "1/2", "muỗng cà phê");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Ngâm nếp trong 8 tiếng hoặc qua đêm. Sau đó làm ráo nước rồi trộn 1 ít muối vào và hấp 45 - 60 phút.", null);
        Step step2 = new Step(2, "Rửa sạch gà với muối rồi cắt thịt ra bỏ xương. Ướp gia vị vào trộn lên và để khoảng 20 phút. Cắt nhỏ hành ra và phi lên. Hạt sen sau khi rửa sạch thì ướp thêm 1 ít muối rồi luộc trong 15 phút, sau đó vớt ra để ráo nước.", null);
        Step step3 = new Step(3, "Cho xôi và hạt sen vào tô thêm gia vị vào sau đó trộn đều.", null);
        Step step4 = new Step(4, "Chiên gà vàng đều các mặt sau đó vớt ra để ráo dầu và cắt thành những miếng nhỏ.", null);
        Step step5 = new Step(5, "Cho lá sen vào tô thêm gà và xôi đã trộn vào, gói lá sen lại rồi hấp trong 20 phút", null);
        Step step6 = new Step(6, "Lấy phần xôi gói lá sen cho ra dĩa, dùng kéo cắt lá sen ra và trang trí thêm dầu hành và 1 ít hành phi", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(gaoNep, duiGaGocTu, laSenTuoi, saBam, hanhBam, toiXay, hanhLa, hanhPhi, hatSenKho, nuocTuong, muoi, duong);

        steps = Arrays.asList(step1, step2, step3, step4, step5, step6);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Xôi gà lá sen", "Xôi dẻo thơm thấm đẫm hương lá sen thơm ngát hòa cùng vị bùi bùi của hạt sen và thịt gà chiên đậm đà gia vị.",
                "https://cdn.tgdd.vn/2021/07/CookRecipe/Avatar/xoi-ga-la-sen-ga-xao-thumbnail-1.jpg", "4", 30,
                Arrays.asList("Món hấp"), ingredients, steps);
    }

    private Recipe recipe3() {
        Ingredient banhTrang = new Ingredient("Bánh tráng", "200", "g");
        Ingredient moHeo = new Ingredient("Mỡ heo", "400", "g");
        Ingredient saTe = new Ingredient("Sa tế", "2", "muỗng canh");
        Ingredient giam = new Ingredient("Giấm", "2", "muỗng canh");
        Ingredient saBam = new Ingredient("Sả băm", "2", "muỗng canh");
        Ingredient hanhTimBam = new Ingredient("Hành tím băm", "2", "muỗng canh");
        Ingredient toiBam = new Ingredient("Tỏi băm", "2", "muỗng canh");
        Ingredient hanhTayBam = new Ingredient("Hành tây băm", "2", "muỗng canh");
        Ingredient tuongOt = new Ingredient("Tương ớt", "1", "muỗng canh");
        Ingredient hanhLa = new Ingredient("Hành lá", "1", "nhánh");
        Ingredient muoi = new Ingredient("Muối", "1/2", "muỗng cà phê");
        Ingredient duong = new Ingredient("Đường", "1/2", "muỗng cà phê");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Mỡ heo rửa sạch với muối để ráo nước và cắt thành lát vuông. Hành lá rửa xong cắt nhỏ.", null);
        Step step2 = new Step(2, "Chiên mỡ heo khoảng tầm 15 phút đến khi vàng đều thì tắt bếp.", null);
        Step step3 = new Step(3, "Cho sả, tỏi, hành tím, hành tây băm vào phi thơm rồi cho thêm gia vị và nước vào đảo đều đên khi đặc sánh lại.", null);
        Step step4 = new Step(4, "Thêm hành lá và tóp mỡ vào hộp hợp đảo điều tầm 3 - 5 phút rồi tắt bếp.", null);
        Step step5 = new Step(5, "Cho bánh tráng vào thao, cho nước sốt tóp mỡ lên và trộn đều. Sau đó cho ra dĩa.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(banhTrang, moHeo, saTe, giam, saBam, hanhTimBam, toiBam, hanhTayBam, tuongOt, hanhLa, muoi, duong);

      steps=  Arrays.asList(step1, step2, step3, step4, step5);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Bánh tráng trộn tốp mỡ", "Dĩa bánh tráng trộn tốp mỡ vô cùng đẹp mắt mà hương vị thơm ngon, lạ miệng.",
                "https://cdn.tgdd.vn/2021/10/CookRecipe/GalleryStep/thanh-pham-598.jpg", "4", 30,
                Arrays.asList("Ăn vặt"), ingredients, steps);
    }

    private Recipe recipe4() {
        Ingredient caBacMa = new Ingredient("Cá bạc má", "5", "con");
        Ingredient hanhLaBam = new Ingredient("Hành lá băm", "50", "g");
        Ingredient gung = new Ingredient("Gùng", "5", "g");
        Ingredient toi = new Ingredient("Tỏi", "10", "g");
        Ingredient ot = new Ingredient("Ớt", "1", "trái");
        Ingredient chanh = new Ingredient("Chanh", "1", "trái");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Gừng cạo sạch vỏ, cắt lát mỏng và đập dập. Tỏi và ớt làm sạch băm nhỏ.", null);
        Step step2 = new Step(2, "Lấy sạch ruột cá, cắt vay làm sạch vẩy rồi rửa lại với nước muối. Cho gừng lên cá, dầu ăn cùng với hạt niêm để ướp chờ hấp.", null);
        Step step3 = new Step(3, "Cho vào chém nước mắm, đường nước cốt chanh, nước và khuấy nhẹ tay. Sau đó cho thêm tỏi và ớt băm vào.", null);
        Step step4 = new Step(4, "Chiên hành băm với hạt niêm đến khi hành hơi săn lại thì tắt bếp.", null);
        Step step5 = new Step(5, "Hấp cá đã ướp trong khoảng 15 phút. Lấy ra và cho mỡ hành lên cá.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(caBacMa, hanhLaBam, gung, toi, ot, chanh);

        steps = Arrays.asList(step1, step2, step3, step4, step5);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Cá bạc má hấp", "Cá bạc má hấp tươi ngon,kết hợp với mỡ hành thơm phức beo béo cuốn với bánh tráng và rau sống",
                "https://cdn.tgdd.vn/2020/06/CookRecipe/Avatar/cach-nau-ca-bac-ma-hap-gung-cuon-banh-trang-thumbnail.jpg", "4", 30,
                Arrays.asList("Món hấp"), ingredients, steps);
    }

    private Recipe recipe5() {
        Ingredient thitBo = new Ingredient("Thịt bò", "500", "g");
        Ingredient hatThiLa = new Ingredient("Hạt thì là", "100", "g");
        Ingredient botOt = new Ingredient("Bột ớt", "1.5", "muỗng cà phê");
        Ingredient ruouTrang = new Ingredient("Rượu trắng", "1/2", "muỗng cà phê");
        Ingredient nuocTuong = new Ingredient("Nước tương", "2", "muỗng cà phê");
        Ingredient dauHao = new Ingredient("Dầu hào", "1", "muỗng canh");
        Ingredient dauAn = new Ingredient("Dầu ăn", "3", "muỗng canh");
        Ingredient muoi = new Ingredient("Muối", "1/2", "muỗng cà phê");
        Ingredient tieuXay = new Ingredient("Tiêu xay", "1/2", "muỗng cà phê");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Dùng khăn ẩm sạch lau sơ trên bề mặt của thịt bò. Sau đó, cắt thành từng khối vuông vừa ăn.", null);
        Step step2 = new Step(2, "Cho vào chén thịt bò vừa cắt, muối, tiêu xay, rượu trắng, dầu hào. Sau đó trộn đều lên rồi để trong 2 tiếng.", null);
        Step step3 = new Step(3, "Để thì là vào chảo đảo đều trong 3 phút. Sau đó thêm bột ớt vào rồi rang trong 2 phút và tắt bếp. Xay hỗn hợp vừa rồi đến khi nhuyễn mịn rồi cho ra chén.", null);
        Step step4 = new Step(4, "Xào thịt bò 5 - 7 phút rồi tắt bếp.", null);
        Step step5 = new Step(5, "Vớt thịt bò ra cho qua 1 chảo khác, sấy khô 5 phút với lửa vừa.", null);
        Step step6 = new Step(6, "Thêm hỗn hợp đã xay vào đảo đều 7 - 10 phút thêm gia vị vừa ăn rồi tắt bếp.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(thitBo, hatThiLa, botOt, ruouTrang, nuocTuong, dauHao, dauAn, muoi, tieuXay);

        steps = Arrays.asList(step1, step2, step3, step4, step5, step6);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Khô bò viên", "khô bò dai dai mềm mềm đậm đà gia vị, quyện với 1 chút cay the.",
                "https://cdn.tgdd.vn/2021/07/CookProduct/1200(2)-1200x676.jpg", "4", 30,
                Arrays.asList("Ăn vặt"), ingredients, steps);
    }

    private Recipe recipe6() {
        Ingredient chanh = new Ingredient("Chanh", "1", "quả");
        Ingredient kemTuoi = new Ingredient("Kem tươi", "150", "ml");
        Ingredient botGelatin = new Ingredient("Bột gelatin", "3", "g");
        Ingredient suaDac = new Ingredient("Sữa đặc", "100", "g");
        Ingredient nuocCotChanh = new Ingredient("Nước cốt chanh", "25", "ml");
        Ingredient duong = new Ingredient("Đường", "40", "g");

// 1.Tạo đối tượng Step
        Step step1 = new Step(1, "Dùng dao bào bào nhuyễn khoảng 1 muỗng cà phê vỏ chanh. Cho bột gelatin và nước lọc vào chén ngâm khoảng 15 phút.", null);
        Step step2 = new Step(2, "Cho bột gelatin đã ngâm nở vào nước cốt chanh rồi đem hấp cách thủy khoảng 3 phút.", null);
        Step step3 = new Step(3, "Cho sữa đặc và phần vỏ chanh đã bào vào hỗn hợp khuấy đều.", null);
        Step step4 = new Step(4, "Cho kem tươi và đường vào máy xay sinh tố và xay khoảng 3 phút. Sau đó cho sữa đặc, gelatin và chanh vào xay thêm khoảng 2 phút.", null);
        Step step5 = new Step(5, "Cho hỗn hợp kem vào khuôn rồi để ở ngăn đông lạnh 3 - 4 tiếng.", null);

// 2. Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(chanh, kemTuoi, botGelatin, suaDac, nuocCotChanh, duong);

        steps = Arrays.asList(step1, step2, step3, step4, step5);

// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Kem chanh sửa", "Bún tươi và thơm phức được kết hợp với chả giò giòn rụm, rau sống và nước chấm đậm đà.",
                "https://cdn.tgdd.vn/2021/09/CookProduct/kemchanh1200-1200x676.jpg", "4", 30,
                Arrays.asList("Ăn vặt"), ingredients, steps);
    }

    private Recipe recipe7() {
        Ingredient suaTuoi = new Ingredient("Sữa tươi", "300", "ml");
        Ingredient kemSocola = new Ingredient("Kem socola", "2", "muông canh");
        Ingredient banhOreo = new Ingredient("Bánh Oreo", "5", "cái");

// 1.Tạo đối tượng
        Step step1 = new Step(1, "Xay bánh Oreo, kem socola, sữa tươi đến khi hỗn hợp nhuyễn và hòa vào nhau.", null);
        Step step2 = new Step(2, "Cho hỗn hợp đã xay ra lý.", null);
        Step step3 = new Step(3, "Lấy bánh Oreo bẻ nhỏ và trang trí lên bề mặt.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(suaTuoi, kemSocola, banhOreo);

        steps = Arrays.asList(step1, step2, step3);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Socola đá xay", "Socola đã xay mát lạnh .",
                "https://cdn.tgdd.vn/2020/11/CookProduct/1-1200x676-9.jpg", "4", 30,
                Arrays.asList("Ăn vặt"), ingredients, steps);
    }

    private Recipe recipe8() {
        Ingredient dauPhong = new Ingredient("Đậu phộng", "40", "g");
        Ingredient botNep = new Ingredient("Bột Nếp", "100", "g");
        Ingredient duongCatTrang = new Ingredient("Đường cát trắng", "200", "g");
        Ingredient nuocLoc = new Ingredient("Nước lọc", "100", "ml");
        Ingredient suaDac = new Ingredient("Sữa đặc", "1", "hộp");
        Ingredient duaNaoSoi = new Ingredient("Dừa nạo sợi", "50", "g");
        Ingredient chanh = new Ingredient("Chanh", "1", "trái");
        Ingredient banhTrangNgot = new Ingredient("Bánh tráng ngọt", "1", "gói");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Đổ đường vào nồi với nước lọc. Bắc nồi lên bếp, lửa vừa. Khuấy nhẹ trong vòng 20 phút.", null);
        Step step2 = new Step(2, "Rang đậu phộng với muối trên lửa nhỏ đến khi đậu phộng nứt ra và còn màu vàng thì tắt bếp.", null);
        Step step3 = new Step(3, "Rang bột nếp ở lửa nhỏ, dùng đũa đảo đều trong khoảng 10 phút thì tắt bếp", null);
        Step step4 = new Step(4, "Đổ nước đường ra tô đã quét dầu ăn sẵn, để nguội trong thau nước. Khi đường còn ấm, kéo đường đến khi chuyển sang màu trắng đục thì cho ra giấy.", null);
        Step step5 = new Step(5, "Nhúng phần đường qua bột nếp đã rang. Kéo nhẹ rồi quấn lại đến khi thành sợi chỉ là được.", null);
        Step step6 = new Step(6, "Xếp bánh tráng ra dĩa. Cho 1 ít dừa nạo sợi, kẹo chỉ, dậu phộng giã nhuyễn và sửa đặc lên. Cho thêm 1 lớp bánh tráng lên rồi gấp đôi lại.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(dauPhong, botNep, duongCatTrang, nuocLoc, suaDac, duaNaoSoi, chanh, banhTrangNgot);

        steps = Arrays.asList(step1, step2, step3, step4, step5, step6);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Kẹo chỉ", "Bánh tráng giòn cùng lớp kẹo chỉ dai dai.",
                "https://cdn.tgdd.vn/2020/08/CookRecipe/GalleryStep/thanh-pham-633.jpg", "4", 30,
                Arrays.asList("Ăn vặt"), ingredients, steps);
    }

    private Recipe recipe9() {
        Ingredient ucGa = new Ingredient("Ức gà", "3", "cái");
        Ingredient toi = new Ingredient("Tỏi", "3", "tép");
        Ingredient ot = new Ingredient("Ớt", "3", "quả");
        Ingredient nguViHuong = new Ingredient("Ngũ vị hương", "1", "muỗng canh");
        Ingredient dauAn = new Ingredient("Dầu ăn", "200", "ml");
        Ingredient muoi = new Ingredient("Muối", "1/2", "muỗng cà phê");
        Ingredient duong = new Ingredient("Đường", "1/2", "muỗng cà phê");
        Ingredient hatNem = new Ingredient("Hạt nêm", "1/2", "muỗng cà phê");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Ức gà rửa sạch rồi để ráo nước. Sau đó dùng dao chặt thành từng miếng nhỏ, cho ra dĩa.", null);
        Step step2 = new Step(2, "Cho vào dĩa thịt gà, ớt băm nhuyễn, hạt niêm, đường, muối, ngũ vị hương rồi trộn đều và ướp khoảng 20 phút.", null);
        Step step3 = new Step(3, "Chiên gà đến khi thịt gà mềm và vàng đều 2 mặt. Sau đó gắp ra dĩa.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(ucGa, toi, nguViHuong, dauAn, muoi, duong, hatNem);

        steps = Arrays.asList(step1, step2, step3);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Gà chiên ngũ vị hương", "Món gà chiên thấm đều gia vị ướp, vừa mềm mềm ngọt nước mà còn thơm mùi ngũ vị hương.",
                "https://cdn.tgdd.vn/2021/07/CookRecipe/Avatar/ga-chien-ngu-vi-huong-thumbnail-3.jpg", "4", 30,
                Arrays.asList("Món chính"), ingredients, steps);
    }

    private Recipe recipe10() {
        Ingredient muc = new Ingredient("Mực", "600", "g");
        Ingredient dauOliu = new Ingredient("Dầu Oliu", "3", "muỗng canh");
        Ingredient thiLa = new Ingredient("Thì là", "15", "g");
        Ingredient toi = new Ingredient("Tỏi", "10", "g");
        Ingredient toiBam = new Ingredient("Tỏi băm", "1", "muỗng canh");
        Ingredient mayonnaise = new Ingredient("Mayonnaise", "6", "muỗng canh");
        Ingredient nuocCotChanh = new Ingredient("Nước cốt chanh", "3", "muỗng canh");
        Ingredient muoi = new Ingredient("Muối", "1", "muỗng cà phê");
        Ingredient tieu = new Ingredient("Tiêu", "1", "muỗng cà phê");
        Ingredient otBot = new Ingredient("Ớt bột", "1", "muỗng cà phê");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Mực rửa sạch, để ráo, cắt nhỏ. Tỏi bóc vỏ, đập dập. Thì là rửa sạch, cắt nhỏ.", null);
        Step step2 = new Step(2, "Cho mực vào tô, thêm dầu oliu, muối, ớt bột, tép tỏi, tiêu, thì là. Trộn đều và để ướp khoảng 30 phút.", null);
        Step step3 = new Step(3, "Cho bột ra tô. Bắc chảo lên bếp, thêm 1 bát dầu ăn đợi nóng.", null);
        Step step4 = new Step(4, "Lăn mực qua bột. Sau đó chiên vàng đều 2 mặt rồi vớt ra để ráo dầu.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(muc, dauOliu, thiLa, toi, toiBam, mayonnaise, nuocCotChanh, muoi, tieu, otBot);

        steps = Arrays.asList(step1, step2, step3, step4);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Mực chiên thì là", "Món mực chiên thì là với lớp vỏ giòn rụm kết hợp mùi thơm của lá thì là.",
                "https://cdn.tgdd.vn/Files/2020/01/08/1230561/-video-cach-lam-muc-chien-gion-thi-la-ngon-la-mieng-de-lam-tai-nha-1.png", "4", 30,
                Arrays.asList("Ăn vặt"), ingredients, steps);
    }

    private Recipe recipe11() {
        Ingredient tauHuChien = new Ingredient("Tàu hủ chiên", "100", "g");
        Ingredient bunTuoi = new Ingredient("Bún tươi", "400", "g");
        Ingredient caChua = new Ingredient("Cà chua", "1", "trái");
        Ingredient thom = new Ingredient("Thơm", "1/4", "trái");
        Ingredient namRom = new Ingredient("Nấm rơm", "200", "g");
        Ingredient sa = new Ingredient("Sả", "4", "nhánh");
        Ingredient me = new Ingredient("Me", "40", "g");
        Ingredient muoi = new Ingredient("Muối", "3", "muỗng cà phê");
        Ingredient duong = new Ingredient("Đường", "2.5", "muỗng cà phê");
        Ingredient ot = new Ingredient("Ớt", "1", "trái");
        Ingredient rau = new Ingredient("Rau", "200", "g");
        Ingredient hatNemChay = new Ingredient("Hạt nêm chay", "2", "muỗng canh");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Rửa sạch nấm rơm, sả băm nhuyễn 2 cây, 2 cây đập dập, thơm cắt lát. Me ngâm với nước nóng và lọc lấy khoảng 2 muỗng canh.", null);
        Step step2 = new Step(2, "Bắc nồi lên 1 muỗng canh dầu ăn, dầu nóng cho sả băm vào phi thơm, cho cà chua xào chín.", null);
        Step step3 = new Step(3, "Cho 1.5 lít nước vào, cho sả cây đập dập, thơm vào nấu sôi, nêm các gia vị vào, nêm nếm vừa ăn. ", null);
        Step step4 = new Step(4, "Cho ớt, đậu hủ chiên, nấm rơm vào nấu khoảng 10 phút nữa rồi tắt bếp.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(tauHuChien, bunTuoi, caChua, thom, namRom, sa, me, muoi, duong, ot, rau, hatNemChay);

        steps = Arrays.asList(step1, step2, step3, step4);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Bún Thái chay", "Món bún Thái chay với vị chua nhẹ của me, cùng vị cay nồng của ớt.",
                "https://cdn.tgdd.vn/Files/2016/08/15/875237/cach-lam-bun-thai-chay-chua-cay-hap-dan-3.jpg", "4", 30,
                Arrays.asList("Món chiên"), ingredients, steps);
    }

    private Recipe recipe12() {
        Ingredient laGetalin = new Ingredient("lá gelatin", "10", "g");
        Ingredient suaTuoi = new Ingredient("Sữa tươi", "100", "ml");
        Ingredient nuocDua = new Ingredient("Nước dừa", "300", "ml");
        Ingredient nuocEpThanhLongDo = new Ingredient("Nước ép thanh long đỏ", "20", "ml");
        Ingredient vaiNgam = new Ingredient("Vải ngâm", "1", "chén");
        Ingredient laHuongThao = new Ingredient("Lá hương thảo", "1", "nhánh");
        Ingredient kemTuoi = new Ingredient("Kem tươi", "70", "g");
        Ingredient botCacao = new Ingredient("Bột cacao", "10", "g");
        Ingredient hanhNhanLat = new Ingredient("Hạnh nhân lát", "10", "g");
        Ingredient duong = new Ingredient("Đường", "30", "g");

// 1.Tạo đối tượng
        Step step1 = new Step(1, "Ngâm lá getalin trong nước lạnh 10 -15 phút đến khi nở mềm thi vớt ra, bóp nhẹ để ráo nước.", null);
        Step step2 = new Step(2, "Đun sôi sữa tươi rồi cho kem tươi và đường vào rồi khuấy đều. Sau đó cho thêm lá getalin vào nấu đến khi hòa tan hoàn toàn.", null);
        Step step3 = new Step(3, "Múc rau câu sữa ra 2 chén khác nhau. Chén 1 cho bột cacao vào khuấy đều. Chén 2 cho nước thanh long đỏ vào rồi khuấy đều. ", null);
        Step step4 = new Step(4, "Cho rau câu sữa vào khuôn đợi khoảng 1 tiếng rau câu đông lại thì gỡ ra khỏi khuôn.", null);
        Step step5 = new Step(5, "Cho nước dừa vào tô thêm nước vải và vải ngâm vào cùng rồi khuấy đều. Cuối cùng thì cho thạch vào tô.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(laGetalin, suaTuoi, nuocDua, nuocEpThanhLongDo, vaiNgam, laHuongThao, kemTuoi, botCacao, duong, hanhNhanLat);

        steps = Arrays.asList(step1, step2, step3, step4);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Chè khúc bạch", "Chè khúc bạch nước vải ngâm ngọt dịu ăn cùng với thạch mềm dai.",
                "https://cdn.tgdd.vn/2021/09/CookRecipe/GalleryStep/thanh-pham-2070.jpg", "4", 30,
                Arrays.asList("Ăn vặt"), ingredients, steps);
    }

    private Recipe recipe13() {
        Ingredient namMo = new Ingredient("Nấm mỡ", "200", "g");
        Ingredient otChuongXanh = new Ingredient("Ớt chuông xanh", "10", "g");
        Ingredient otChuongDo = new Ingredient("Ớt chuông đỏ", "10", "g");
        Ingredient hanhBoaRoBam = new Ingredient("Hành boa - rô băm", "15", "g");
        Ingredient ngoRiBam = new Ingredient("Ngò rí băm", "10", "g");
        Ingredient duong = new Ingredient("Đường", "50", "g");
        Ingredient muoi = new Ingredient("Muối", "10", "g");
        Ingredient tieu = new Ingredient("Tiêu ", "10", "g");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Rửa sơ nấm, cắt bỏ phần chân dơ. Khứa nhẹ hình ca-rô. Thái hạt lựu nhỏ ớt chuông xanh và đỏ.", null);
        Step step2 = new Step(2, "Cho hết nấm mỡ vào chiên cho đến khi chân nấm chuyển sang màu vàng nâu thì vớt ra.", null);
        Step step3 = new Step(3, "Phi thơm hành rồi cho nấm mỡ vào xào với lửa lớn. Rắc ít muối và đường, đảo đều và tắt bếp.", null);
        Step step4 = new Step(4, "Rắc thêm tiêu và bày ra đĩa, trang trí lên bề mặt với ít ớt chuông xanh - đỏ và ngò rí.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(namMo, otChuongXanh, otChuongDo, hanhBoaRoBam, ngoRiBam, duong, muoi, tieu);

        steps = Arrays.asList(step1, step2, step3, step4);
// 3. Tạo đối tượng Recipe
        Recipe recipe13 = new Recipe("null", "Nấm xào", "Vị ngọt, dai đặc trưng của món nấm mỡ xào muối tiêu.",
                "https://cdn.tgdd.vn/2020/08/CookRecipe/GalleryStep/thanh-pham-597.jpg", "4", 30,
                Arrays.asList("Món xào"), ingredients, steps);
        return recipe13;
    }

    private Recipe recipe14() {
        Ingredient thitBaChi = new Ingredient("Thị ba chỉ", "300", "g");
        Ingredient trungCut = new Ingredient("Trứng cút", "20", "quả");
        Ingredient hanhLa = new Ingredient("Hành lá", "1", "nhánh");
        Ingredient otSung = new Ingredient("Ớt sừng", "1", "quả");
        Ingredient giaViThitKho = new Ingredient("Gia vị thịt kho", "1", "gói");
        Ingredient nuocMam = new Ingredient("Nước mắm", "1", "muỗng cà phê");
        Ingredient nuocMau = new Ingredient("Nước màu", "1", "muỗng cà phê");
        Ingredient botNgot = new Ingredient("Bột ngọt ", "1/4", "muỗng cà phê");
        Ingredient tieuXay = new Ingredient("Tiêu xay ", "1/2", "muỗng cà phê");
        Ingredient muoi = new Ingredient("Muối ", "1/2", "muỗng cà phê");
        Ingredient dauAn = new Ingredient("Dầu ăn ", "1", "lít");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Thịt ba chỉ rửa sạch để ráo sau đó cắt thành những miếng vuông nhỏ vừa ăn. ", null);
        Step step2 = new Step(2, "Trứng cút rửa sạch rồi luộc 7 - 10 phút cho chín. Đợi trứng nguội thì bóc vỏ ra rồi rửa lại 1 lần nữa. Ớt sừng và hành lá rửa sạch rồi băm nhỏ.", null);
        Step step3 = new Step(3, "Cho thịt vào tô ướp với gói gia vị, bột ngọt, muối, nước mắm, nước màu, tiêu xay, trộn đều để khoảng 30 phút.", null);
        Step step4 = new Step(4, "Chiên trứng cút vàng đều rồi vớt ra để cho ráo dầu.", null);
        Step step5 = new Step(5, "Xào thịt khoảng 3 phút cho thịt săn lại rồi đổ vào nồi kho. Thêm nước lọc vào nồi để kho thịt.", null);
        Step step6 = new Step(6, "Khi thịt sôi thì cho phần trứng cút đã chiên, 1 chút ớt băm vào nồi. Kho trên lửa nhỏ khoảng 30 phút đến khi thịt mềm thì tắt bếp", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(thitBaChi, trungCut, hanhLa, otSung, giaViThitKho, nuocMam, botNgot, nuocMau, muoi, tieuXay, dauAn);

        steps = Arrays.asList(step1, step2, step3, step4, step5, step6);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Thịt kho tàu", "Món thịt kho tàu thơm ngon nóng hổi thịt mềm béo ngậy, trứng cút dai dai.",
                "https://cdn.tgdd.vn/2022/01/CookRecipe/GalleryStep/thanh-pham-203.jpg", "4", 30,
                Arrays.asList("Món kho"), ingredients, steps);
    }

    private Recipe recipe15() {
        Ingredient mangTuoi = new Ingredient("Măng tươi", "800", "g");
        Ingredient thitBaChi = new Ingredient("Thịt ba chỉ", "500", "g");
        Ingredient tomTuoi = new Ingredient("Tôm tươi", "150", "g");
        Ingredient dauPhongRang = new Ingredient("Đậu phộng rang", "100", "g");
        Ingredient rauThom = new Ingredient("Rau thơm", "1", "bó");
        Ingredient otHiem = new Ingredient("Ớt hiểm", "3", "trái");
        Ingredient otSung = new Ingredient("Ớt sừng", "1", "trái");
        Ingredient dauAn = new Ingredient("Dầu ăn ", "2", "muỗng canh");
        Ingredient nuocMam = new Ingredient("Nước mắm ", "2", "muỗng canh");
        Ingredient muoi = new Ingredient("Muối ", "1", "muỗng cà phê");
        Ingredient duong = new Ingredient("Đường ", "1/2", "muỗng cà phê");
        Ingredient botNgot = new Ingredient("Bột ngọt ", "1/4", "muỗng cà phê");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Măng tươi lột vỏ rồi dùng dao cắt nhỏ, rửa sơ với nước rồi luộc ở lửa lớn với muối.", null);
        Step step2 = new Step(2, "Luộc măng 15 - 20 phút rồi xả nước 3 - 4 lần sau đó cho ra rổ để ráo nước rồi cắt thành sợi nhỏ và cho ra rổ.", null);
        Step step3 = new Step(3, "Chà xát thịt heo với muối sau đó rửa sạch bằng nước. Sau đó cắt thành các lát mỏng. Tôm rửa sạch rồi luộc 5 -  phút, vớt ra để nguội rồi lột vỏ. ", null);
        Step step4 = new Step(4, "Ngâm rau thơm trong nước muối 5 phút, rửa lại với nước rồi cho ra rổ. Tỏi bóc vỏ, ớt bỏ cuống rửa sạch rồi giã nhuyễn. Giã hơi dập đậu phộng rang bóc vỏ sẵn.", null);
        Step step5 = new Step(5, "Cho toàn bộ tỏi ớt giã nhuyễn vào chảo phi cho thơm vàng, Thêm thịt heo và gia vị vào đảo đều 10 - 15 phút nếm lại gia vị rồi tắt bếp.", null);
        Step step6 = new Step(6, "Cho măng vào tô thêm thịt,tôm và gia vị vào trộn đều. Cuối cùng là thêm rau thơm vào rồi trộn thêm 1 lần nữa, sau đó thêm đậu phộng vào.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(mangTuoi, thitBaChi, tomTuoi, dauPhongRang, rauThom, otHiem, otSung, dauAn, nuocMam, muoi, duong, botNgot);

        steps = Arrays.asList(step1, step2, step3, step4);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Gỏi măng tôm thịt", "Món gỏi măng tôm thịt sự kết hợp giữa vị ngọt của măng, béo của thịt, ngọt của tôm tươi.",
                "https://cdn.tgdd.vn/2021/10/CookDish/cach-lam-goi-mang-tom-thit-thom-ngon-don-gian-tai-nha-avt-1200x676-1.jpg", "4", 30,
                Arrays.asList("Món gỏi"), ingredients, steps);
    }

    private Recipe recipe16() {
        Ingredient caPhao = new Ingredient("Cà pháo", "1", "kg");
        Ingredient toi = new Ingredient("Tỏi", "1", "củ");
        Ingredient muoiAn = new Ingredient("Muối ăn", "1/2", "kg");
        Ingredient gung = new Ingredient("Gừng", "2", "củ");
        Ingredient otXay = new Ingredient("Ớt xay", "200", "g");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Cà pháo cắt cuốn, ngâm trong nước muối rồi rửa sạch. Tỏi bóc vỏ, đập dập.", null);
        Step step2 = new Step(2, "Đun sôi nước, thêm muối và tỏi vào rồi khuấy đều. Cho cà pháo và nước muối đun sôi đã để nguội vào hủ lớn. Đậy kín nắp 7 ngày.", null);
        Step step3 = new Step(3, "Gừng gọt vỏ, thái sợi mỏng. Pha nước sôi để nguội với nước mắm và đường, khuấy đều cho tan hết rồi đun sôi. Để nguội rồi trộn ớt xay với gừng thái sợi vào.", null);
        Step step4 = new Step(4, "Sau khi ngâm đủ 7 ngày, lấy cà pháo ra, rửa sạch rồi cắt đôi. Trộn cà pháo với hỗn hợp gia vị rồi cho vào hộp, để 1 ngày.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(caPhao, toi, muoiAn, gung, otXay);

        steps = Arrays.asList(step1, step2, step3, step4);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Cà pháo muối kim chi", "Cà pháo muối kim chi chua cay, giòn tan cực ngon.",
                "https://cdn.tgdd.vn/Files/2018/10/29/1127578/chi-tiet-cach-lam-ca-phao-muoi-kim-chi-chua-cay-gion-tan-cuc-ngon-5-760x367.jpg", "4", 30,
                Arrays.asList("Món chính"), ingredients, steps);
    }

    private Recipe recipe17() {
        Ingredient cuaBien = new Ingredient("Cua biển", "3", "con");
        Ingredient sa = new Ingredient("Sả", "12", "nhánh");
        Ingredient gung = new Ingredient("Gừng", "1", "củ");
        Ingredient tac = new Ingredient("Tắc", "1", "quả");
        Ingredient tieuHat = new Ingredient("Tiêu hạt", "1", "muỗng canh");
        Ingredient muoiHat = new Ingredient("Muối hạt", "1", "muỗng canh");
        Ingredient bia = new Ingredient("Bia", "330", "ml");
        Ingredient dauAn = new Ingredient("Dầu ăn", "2", "muỗng cà phê");
        Ingredient muoi = new Ingredient("Muối", "1/2", "muỗng cà phê");
        Ingredient duong = new Ingredient("Đường", "1/2", "muỗng cà phê");
        Ingredient botNgot = new Ingredient("Bột ngọt", "1/4", "muỗng cà phê");
        Ingredient hatNem = new Ingredient("Hạt nêm", "1/2", "muỗng cà phê");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Giữ nguyên dây buộc trên cua, ngâm cua trong nước đá khoảng 10 phút. Sau đó dùng bàn chải làm sạch cua.", null);
        Step step2 = new Step(2, "Sả rửa sạch rồi dùng dao đập dập. Gừng gọt vỏ rửa sạch rồi thái nhỏ.", null);
        Step step3 = new Step(3, "Giã nhuyễn tiêu hạt và muối hạt. Cho thêm bột ngọt vào rồi trộn đều, vắt thêm vào 1 quả tắc.", null);
        Step step4 = new Step(4, "Xếp sả và gừng vào sau đó cho cua lên trên rồi hấp. Rắc đều lên mai cua muối, bột ngọt, hạt nêm, đường.", null);
        Step step5 = new Step(5, "Cho nước và bia vào sau đó hấp khoảng 7- 12 phút.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(cuaBien, sa, gung, tac, tieuHat, muoiHat, bia, dauAn, muoi, duong, botNgot, hatNem);

        steps = Arrays.asList(step1, step2, step3, step4, step5);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Cua hấp sả", "Món cua biển hấp sả dậy mùi hương của sả và gừng kết hợp với thịt cua thơm lừng.",
                "https://cdn.tgdd.vn/2021/04/CookRecipe/GalleryStep/thanh-pham-190.jpg", "4", 30,
                Arrays.asList("Món hấp"), ingredients, steps);
    }

    public Recipe recipe18() {
        Ingredient hong = new Ingredient("Hồng", "2", "quả");
        Ingredient matOng = new Ingredient("Mật ong", "1/2", "muỗng cà phê");
        Ingredient nuoc = new Ingredient("Nước", "250", "ml");
        // 1.Tạo đối tượng
        Step step1 = new Step(1, "Hồng rửa sạch với nước muối khoảng 10 phút sau đó rửa sạch lại với nước.", null);
        Step step2 = new Step(2, "Dùng dao cắt bỏ phần cuống, cắt đôi quả hồng rồi cắt thành nhiều miếng nhỏ vừa ăn.", null);
        Step step3 = new Step(3, "Tiến hành xay nhuyễn trong khoảng 3 phút hồng, nước lọc và mật ong. Sau đó rót ra ly.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(hong, matOng, nuoc);

        steps = Arrays.asList(step1, step2, step3);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Sinh tố hồng", "Sinh tố hồng thơm ngon, vị ngọt vừa phải không bị gắt, uống rất bắt miệng, không chỉ là thức uống giải khát mà còn rất tốt cho sức khỏe.",
                "https://cdn.tgdd.vn/2021/10/CookRecipe/GalleryStep/thanh-pham-1247.jpg", "4", 30,
                Arrays.asList("Ăn vặt"), ingredients, steps);
    }

    public Recipe recipe19() {
        Ingredient namBo = new Ingredient("Nạm bò", "1", "kg");
        Ingredient thitCoBo = new Ingredient("Thịt cổ bò", "1", "kg");
        Ingredient xuongBo = new Ingredient("Xương bò", "1", "kg");
        Ingredient sa = new Ingredient("Sả ", "10", "cây");
        Ingredient caiTrang = new Ingredient("Cải trắng", "1", "củ");
        Ingredient gung = new Ingredient("Gừng", "1", "củ");
        Ingredient hanhTim = new Ingredient("Hành tím", "2", "củ");
        Ingredient muoi = new Ingredient("Muối", "2", "muỗng canh");
        Ingredient duong = new Ingredient("Đường", "2", "muỗng canh");
        Ingredient dauHu = new Ingredient("Đậu hũ", "2", "miếng");
        Ingredient muopHuong = new Ingredient("Mướp hương", "1", "trái");
// 1.Tạo đối tượng
        Step step1 = new Step(1, "Củ cải trắng gọt vỏ, rửa sạch rồi cắt khúc khoảng 5cm.Sả rửa sạch, cắt khúc dài 8 - 10cm, đập dập. Lá sả cột lại thành bó.Gừng, hành tím nướng sơ trên lửa cho thơm. Sau đó cạo vỏ và rửa sạch.", null);
        Step step2 = new Step(2, "Xương bò rửa sạch, chặt thành từng khúc khoảng 5cm. Luộc sơ khoảng 15 phút. Sau đó, vớt ra rửa sạch, để ráo.Cổ bò và nạm bò rửa sạch, để ráo.", null);
        Step step3 = new Step(3, "Đậu hũ cắt miếng vừa ăn. Mướp hương gọt vỏ, rửa sạch, cắt khúc vừa ăn. Rửa sạch các loại rau ăn kèm, để ráo.", null);
        Step step4 = new Step(4, "Bắc nồi lên bếp, cho vào khoảng 6 lít nước lã và đun sôi.cho tiếp xương bò, thịt cổ bò, nạm bò, sả cây dập dập, lá sả, gừng nướng, hành nướng, củ cải trắng vào nồi.", null);
        Step step5 = new Step(5, "Hầm bò trên lửa lớn khoảng 15 phút để ra hết bọt bẩn, vớt lớp bọc bẩn ra. Thêm muối, hạt nêm, đường hầm trên lửa nhỏ 2 tiếng.", null);
// 2.Tạo danh sách nguyên liệu và bước nấu
        ingredients = Arrays.asList(namBo, thitCoBo, xuongBo, sa, caiTrang, gung, hanhTim, muoi, duong, dauHu, muopHuong);

        steps = Arrays.asList(step1, step2, step3, step4, step5);
// 3. Tạo đối tượng Recipe
        return new Recipe("null", "Bò hầm sả", "Bò hầm sả khi ăn có vị ngọt thanh từ nước dùng xương bò, hương thơm của sả, thịt bò có độ mềm vừa phải.",
                "https://cdn.tgdd.vn/Files/2020/05/31/1259826/cach-nau-bo-ham-sa-thom-ngon-don-gian-tai-nha-5.jpg", "4", 30,
                Arrays.asList("Món nước"), ingredients, steps);
    }


}
