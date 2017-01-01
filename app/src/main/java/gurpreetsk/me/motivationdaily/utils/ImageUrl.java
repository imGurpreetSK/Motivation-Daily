package gurpreetsk.me.motivationdaily.utils;

/**
 * Created by Gurpreet on 27/12/16.
 */

public class ImageUrl {

    /**
     * @param authorName
     * @return url to image
     * P.S. Please don't judge for this hack, couldn't find a good API :P
     */
    public static String getAuthorImage(String authorName) {

        switch (authorName) {
            case "Abraham Lincoln":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Abraham_Lincoln_O-77_matte_collodion_print.jpg/220px-Abraham_Lincoln_O-77_matte_collodion_print.jpg";
            case "Albert Einstein":
                return "http://www.baybul.net/wp-content/uploads/2012/05/AlbertEinstein.jpg";
            case "Alexander Pope":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Alexander_Pope_by_Michael_Dahl.jpg/220px-Alexander_Pope_by_Michael_Dahl.jpg";
            case "Aristotle":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Aristotle_Altemps_Inv8575.jpg/800px-Aristotle_Altemps_Inv8575.jpg";
            case "Barack Obama":
                return "https://lh6.googleusercontent.com/-2lJYGtfXKwQ/AAAAAAAAAAI/AAAAAAAB2HQ/QSmIw0nQN_c/photo.jpg";
            case "Benjamin Franklin":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/BenFranklinDuplessis.jpg/220px-BenFranklinDuplessis.jpg";
            case "Bill Gates":
                return "https://upload.wikimedia.org/wikipedia/commons/1/19/Bill_Gates_June_2015.jpg";
            case "Bob Marley":
                return "https://img11.deviantart.net/3187/i/2008/091/c/e/bob_marley_by_antiemo.jpg";
            case "Bruce Lee":
                return "https://upload.wikimedia.org/wikipedia/commons/c/ca/Bruce_Lee_1973.jpg";
            case "Buddha":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Buddha_in_Sarnath_Museum_%28Dhammajak_Mutra%29.jpg/220px-Buddha_in_Sarnath_Museum_%28Dhammajak_Mutra%29.jpg";
            case "C S Lewis":
                return "https://upload.wikimedia.org/wikipedia/en/thumb/1/1e/C.s.lewis3.JPG/220px-C.s.lewis3.JPG";
            case "Charles Darwin":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Charles_Darwin_seated_crop.jpg/220px-Charles_Darwin_seated_crop.jpg";
            case "Charles Dickens":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/Dickens_Gurney_head.jpg/220px-Dickens_Gurney_head.jpg";
            case "Confucius":
                return "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcT7SMCOCleNBKD2kpoVzxcOdIjr6hkbVxkTPDpv9eN-rege3SCG";
            case "Conor McGregor":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Conor_McGregor%2C_UFC_189_World_Tour_London_%282%29.jpg/220px-Conor_McGregor%2C_UFC_189_World_Tour_London_%282%29.jpg";
            case "Dalai Lama":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Dalailama1_20121014_4639.jpg/220px-Dalailama1_20121014_4639.jpg";
            case "Dr APJ Abdul Kalam":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b0/A._P._J._Abdul_Kalam_in_2008.jpg/230px-A._P._J._Abdul_Kalam_in_2008.jpg";
            case "Dr Seuss":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Ted_Geisel_NYWTS_2_crop.jpg/220px-Ted_Geisel_NYWTS_2_crop.jpg";
            case "Eleanor Roosevelt":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Eleanor-Roosevelt-WH-Portrait.jpg/220px-Eleanor-Roosevelt-WH-Portrait.jpg";
            case "Ellen DeGeneres":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Ellen_DeGeneres_2011.jpg/220px-Ellen_DeGeneres_2011.jpg";
            case "Elon Musk":
                return "https://upload.wikimedia.org/wikipedia/commons/4/49/Elon_Musk_2015.jpg";
            case "Ernest Hemingway":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/ErnestHemingway.jpg/220px-ErnestHemingway.jpg";
            case "Franklin D Roosevelt":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/3/30/FDR_1944_Color_Portrait.tif/lossy-page1-220px-FDR_1944_Color_Portrait.tif.jpg";
            case "Friedrich Nietzsche":
                return "https://upload.wikimedia.org/wikipedia/commons/1/1b/Nietzsche187a.jpg";
            case "George Sand":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/5/54/George_Sand_by_Nadar%2C_1864.jpg/220px-George_Sand_by_Nadar%2C_1864.jpg";
            case "H Mencken":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/5/54/H-L-Mencken-1928.jpg/220px-H-L-Mencken-1928.jpg";
            case "Henry David Thoreau":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Benjamin_D._Maxham_-_Henry_David_Thoreau_-_Restored.jpg/250px-Benjamin_D._Maxham_-_Henry_David_Thoreau_-_Restored.jpg";
            case "Hippocrates":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Hippocrates.jpg/220px-Hippocrates.jpg";
            case "Horace":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Quintus_Horatius_Flaccus.jpg/200px-Quintus_Horatius_Flaccus.jpg";
            case "Isaac newton":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/GodfreyKneller-IsaacNewton-1689.jpg/220px-GodfreyKneller-IsaacNewton-1689.jpg";
            case "J K Rowling":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/J._K._Rowling_2010.jpg/220px-J._K._Rowling_2010.jpg";
            case "Jesus Christ":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/6/63/Cefal%C3%B9_Pantocrator_retouched.jpg/220px-Cefal%C3%B9_Pantocrator_retouched.jpg";
            case "John F Kennedy":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/JFK_WHPO.tif/lossy-page1-220px-JFK_WHPO.tif.jpg";
            case "Julius Caesar":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/C%C3%A9sar_%2813667960455%29.jpg/220px-C%C3%A9sar_%2813667960455%29.jpg";
            case "Khalil Gibran":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Khalil_Gibran.jpg/220px-Khalil_Gibran.jpg";
            case "Kurt Cobain":
                return "https://s-media-cache-ak0.pinimg.com/originals/b1/78/51/b17851790b3d5a8ab85bfe8d431e0384.jpg";
            case "Lao Tzu":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/DaodeTianzun.jpg/250px-DaodeTianzun.jpg";
            case "Leonardo Da Vinci":
                return "http://www.leonardodavinci.net/images/leonardo-da-vinci.jpg";
            case "Lionel messi":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/2015_UEFA_Super_Cup_64_crop.jpg/220px-2015_UEFA_Super_Cup_64_crop.jpg";
            case "Luke Bryan":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ad/LukeBryanApr10.jpg/220px-LukeBryanApr10.jpg";
            case "Mahatma Gandhi":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Portrait_Gandhi.jpg/220px-Portrait_Gandhi.jpg";
            case "Marcus Aurelius":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/L%27Image_et_le_Pouvoir_-_Buste_cuirass%C3%A9_de_Marc_Aur%C3%A8le_ag%C3%A9_-_3.jpg/220px-L%27Image_et_le_Pouvoir_-_Buste_cuirass%C3%A9_de_Marc_Aur%C3%A8le_ag%C3%A9_-_3.jpg";
            case "Marie Curie":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Marie_Curie_c1920.jpg/220px-Marie_Curie_c1920.jpg";
            case "Marilyn Monroe":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/Monroecirca1953.jpg/220px-Monroecirca1953.jpg";
            case "Mark Twain":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Mark_Twain%2C_Brady-Handy_photo_portrait%2C_Feb_7%2C_1871%2C_cropped.jpg/220px-Mark_Twain%2C_Brady-Handy_photo_portrait%2C_Feb_7%2C_1871%2C_cropped.jpg";
            case "Martin King Jr":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Martin_Luther_King%2C_Jr..jpg/220px-Martin_Luther_King%2C_Jr..jpg";
            case "Maya Angelou":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Angelou_at_Clinton_inauguration.jpg/220px-Angelou_at_Clinton_inauguration.jpg";
            case "Mohammad Ali":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Muhammad_Ali_NYWTS.jpg/220px-Muhammad_Ali_NYWTS.jpg";
            case "Mother Teresa":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/MotherTeresa_094.jpg/220px-MotherTeresa_094.jpg";
            case "Napoleon Bonaparte":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Jacques-Louis_David_-_The_Emperor_Napoleon_in_His_Study_at_the_Tuileries_-_Google_Art_Project.jpg/220px-Jacques-Louis_David_-_The_Emperor_Napoleon_in_His_Study_at_the_Tuileries_-_Google_Art_Project.jpg";
            case "Nelson Mandela":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Nelson_Mandela-2008_%28edit%29.jpg/220px-Nelson_Mandela-2008_%28edit%29.jpg";
            case "Nikola Tesla":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/N.Tesla.JPG/220px-N.Tesla.JPG";
            case "Oprah Winfrey":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Oprah_in_2014.jpg/220px-Oprah_in_2014.jpg";
            case "Oscar Wilde":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Oscar_Wilde_Sarony.jpg/220px-Oscar_Wilde_Sarony.jpg";
            case "Pablo Picasso":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Portrait_de_Picasso%2C_1908.jpg/230px-Portrait_de_Picasso%2C_1908.jpg";
            case "Pele":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Pele200802FabioRodriguesPozzebomAgenciaBrasil.jpg/250px-Pele200802FabioRodriguesPozzebomAgenciaBrasil.jpg";
            case "Plato":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Plato_Silanion_Musei_Capitolini_MC1377.jpg/220px-Plato_Silanion_Musei_Capitolini_MC1377.jpg";
            case "Pope Francis":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Franciscus_in_2015.jpg/220px-Franciscus_in_2015.jpg";
            case "Queen Elizabeth II":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Queen_Elizabeth_II_March_2015.jpg/220px-Queen_Elizabeth_II_March_2015.jpg";
            case "Rabindranath Tagore":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Rabindranath_Tagore_unknown_location.jpg/225px-Rabindranath_Tagore_unknown_location.jpg";
            case "Ralph Waldo Emerson":
                return "https://upload.wikimedia.org/wikipedia/commons/d/d5/Ralph_Waldo_Emerson_ca1857_retouched.jpg";
            case "Robert Frost":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Robert_Frost_NYWTS.jpg/220px-Robert_Frost_NYWTS.jpg";
            case "Ronald Reagan":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/Official_Portrait_of_President_Reagan_1981.jpg/220px-Official_Portrait_of_President_Reagan_1981.jpg";
            case "Rumi":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Molana.jpg/220px-Molana.jpg";
            case "Samuel L Jackson":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Samuel_L._Jackson_SDCC_2014_%28cropped%29.jpg/220px-Samuel_L._Jackson_SDCC_2014_%28cropped%29.jpg";
            case "Socrates":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Socrates_Louvre.jpg/220px-Socrates_Louvre.jpg";
            case "Stephen Hawkins":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Stephen_Hawking.StarChild.jpg/220px-Stephen_Hawking.StarChild.jpg";
            case "Stephen King":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Stephen_King%2C_Comicon.jpg/220px-Stephen_King%2C_Comicon.jpg";
            case "Steve Jobs":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Steve_Jobs_with_red_shawl_edit.jpg/220px-Steve_Jobs_with_red_shawl_edit.jpg";
            case "Sun Tzu":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/3/37/Enchoen27n3200.jpg/220px-Enchoen27n3200.jpg";
            case "Sylvia Plath":
                return "https://upload.wikimedia.org/wikipedia/en/thumb/e/e4/Sylvia_plath.jpg/220px-Sylvia_plath.jpg";
            case "Theodore Roosevelt":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/President_Roosevelt_-_Pach_Bros.tif/lossy-page1-220px-President_Roosevelt_-_Pach_Bros.tif.jpg";
            case "Thomas Jefferson":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Official_Presidential_portrait_of_Thomas_Jefferson_%28by_Rembrandt_Peale%2C_1800%29.jpg/220px-Official_Presidential_portrait_of_Thomas_Jefferson_%28by_Rembrandt_Peale%2C_1800%29.jpg";
            case "Tupac Shakur":
                return "https://upload.wikimedia.org/wikipedia/en/thumb/b/b5/Tupac_Amaru_Shakur2.jpg/220px-Tupac_Amaru_Shakur2.jpg";
            case "Ulysses S Grant":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Ulysses_S._Grant_1870-1880.jpg/220px-Ulysses_S._Grant_1870-1880.jpg";
            case "Vincent van Gogh":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Vincent_van_Gogh_-_Self-Portrait_-_Google_Art_Project_%28454045%29.jpg/220px-Vincent_van_Gogh_-_Self-Portrait_-_Google_Art_Project_%28454045%29.jpg";
            case "Walt Disney":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Walt_Disney_1946.JPG/220px-Walt_Disney_1946.JPG";
            case "Warren Buffett":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Warren_Buffett_KU_Visit.jpg/220px-Warren_Buffett_KU_Visit.jpg";
            case "William Blake":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/0/00/William_Blake_by_Thomas_Phillips.jpg/220px-William_Blake_by_Thomas_Phillips.jpg";
            case "William Shakespeare":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Shakespeare.jpg/220px-Shakespeare.jpg";
            case "Winston Churchill":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Sir_Winston_Churchill_-_19086236948.jpg/250px-Sir_Winston_Churchill_-_19086236948.jpg";
            case "Yogi Berra":
                return "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/1953_Bowman_Yogi_Berra.jpg/220px-1953_Bowman_Yogi_Berra.jpg";
        }

        return "";

    }

    public static String getTagImage(String tag) {
        switch (tag) {
            case "art":
                return "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQ4H8yainNego2VH7bPFY-AboXHIlYZBNVXEyo8k8SErEEwh3jHth_c4mE";
            case "experience":
                return "https://www.qualtrics.com/wp-content/uploads/2015/07/CustomerExperienceMaturity.png";
            case "failure":
                return "https://i.kinja-img.com/gawker-media/image/upload/s--u79MUgRQ--/c_scale,f_auto,fl_progressive,q_80,w_800/18s40x6wrmxgujpg.jpg";
            case "friendship":
                return "https://s-media-cache-ak0.pinimg.com/originals/88/ce/c7/88cec73f55f103a01110b6cb18a29933.jpg";
            case "god":
                return "http://cdn.images.express.co.uk/img/dynamic/151/590x/secondary/god-2-463057.jpg";
            case "learning":
                return "https://www.hekademia.com/blog/wp-content/uploads/2015/04/xyleme-personalized-learning_0.png";
            case "life":
                return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHCECQ1RYZNNHJfs4l8fGBnnnsayVGoFeThe5pB2fwCgJ-0zCYHA";
            case "love":
                return "https://image.freepik.com/free-vector/hearts-collection_23-2147512152.jpg";
            case "motivation":
                return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQY-apHGac7Bp0N8pUnKe9ni60JW1Pst3dSTxEyuGxammO5_9J";
            case "success":
                return "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSWUiIOQoux2MJEwu_Q5xhatFJdo8TJD3H78gl63jyKa7nloZe5-Q";
            case "wisdom":
                return "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRUNY_DkTeb5N0GckeVP4xXYeULkyQ69ghDrXalD4--UxRuApir";
        }
        return "";
    }

}
