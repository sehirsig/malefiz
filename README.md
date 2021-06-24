# HTWG Constance - AIN 3 - Software Engineering
# Scala Project - Malefiz Game
## Game Project for the lecture SE

![gamepicture](https://user-images.githubusercontent.com/81407658/114448533-f96ce480-9bd3-11eb-93a7-74dc0941f6c1.jpg)


# Game Rules
Malefiz is a board game for 2 to 4 players. Each player has 5 figures to play with. Every figure starts in their base, at the bottom of the board. The beginning player rolls the dice. The thrown number has to be pulled completly. While walking with the figure, change of direction is not allowed. If a player lands on an enemys figure, the enemy has to put his figure back to his base. Figures are allowed to jump over other figures, but not over barricades. If a player lands on the barricade (with the exact number), he has to set the barricade anywhere on the game board, all black fields are allowed, except the lowest row. The player reaching the top of the gameboard first (with the exact number rolled with the dice) with one figure wins the game.

# How This Game Works

 1. Start the game!
 2. Add some Players (2-4)!
 3. Roll the dice!
 4. Choose the gamefigure!
 5. Move the gamefigure!
 6. Next players turn!

![enter image description here](https://lh3.googleusercontent.com/krqGOhvupUlYfQ9xjWk4xClA_ohwlnnH78OxVCoK8a-yqXFfMlH45zX1w17Nij6_5gSND8b-IpuJUNchyF1HyvDlEInhphEipiwRILpp80_PakCUf2nLnmtoBLtoug2BZAYTcTbcYaGdK42Jx4Ip8zpO2HnTHUDz7HF_dpqTvXxNjbeDjKslFGTsknUV8xKJiQ31311vO72P_ueLW4mesjRddGydhEy2Zw_mjVL_fIQDZWcC6taJzBgQYKdxXRr5d06rKwDHqpqOPrh4w7pK9U-QkQCnCWSZwovOs0PJZ7vFUtmPXb222mRY_CF408LloQKSJ4ircbCT839LGsXjAUwvAmrBE-MdUEcv3IiLgDJWBLTcREbwdNh7erFNUjrpj0CEadDtuSlwKSrZ6DiJcHZOtaJk1LJoZpwSb1DmXLLyKEB81v8LJ3LMeyTNVFhD-inbpJVYj94bn8RQRucr0YOPXfyKFHRgUwqUQh59d0y9orYSHdXLSQMVA4N-_wK8FBhwxj5Yb7WE91rV_StnVDAYqx8ZijdriG3fcj2dvyFt0nPZe5f22qV9kcju6bCKlMtx_gan2MuKZA2wwGT3sl6E9UmGqWFCsdvp0G17cyu-wf_OwowQLxSPuNwJQcKw6n0zAPTx1_aNCCifmk6F57g7o6aS-PxqNTpaVb11u3Df1g8siHac1Q5yWpq8JGXiINy2V9aCt9mgThYkDHBoHVQ=w300-h407-no?authuser=0)![enter image description here](https://lh3.googleusercontent.com/xlw4OuzJg0jwsEJEl0gYjhrB1OycqfPqjMqj9YthjEsgce2RqFVYSi2FT80HPiwIlVtVQ4lp7wwKPQrkjS1VDise3dFczzxyhhnqRsh_fhYb9jhg6ZiUvgwv9xOpcsbslc30GQ7GLYtByXfRRGqoF0vlJUxSSns0lNrFQWk0BqrG6p9ICQ1E7NvtWwj1iQOoZ-QA3HZesyUQjrlI_YZ5TvtLb-dGt0eUsIZmk7pksEYAwjuZlmGOMzn4JRof9Y-S7iXom3XaSa8nikumDWQEwXWkE3VKwt8Byi6hzEggc7RMAoofdbcFINMsq0v0xp3wO9WlLthpZnBCN6wCRTx83XB8Crr6KON08SGCmEstAP3GCZ-WAb0zVczCYZrzh3cathJtW7K_2i1st8U7hP8u63t6AMJsJ1AlSvGv8mTtMU15kDzLn9uzaO-A_Ki4KiS3_45q0tT38vj93RAQHTuzW_xGuPm5gZM2SR18USpXMV5uB9JtQivCp6aJMHbtHB4qAusyLn4dleT278m2_64gwJxL5_fAAAVHOryxUwpjzK24S9IAsOuLz5zD5NlFMCswnbuXPyG7uYTZ92XwLnkmkwQNwxZYp_Yg7XM7tkQZWBX35rHWAOSVAF0uYWaMdlD6IdrYj3Q_Wxn2Nrk0PiImnaIX1GTCAj2tmDgsdLiK53j_U9bhu8ICSJm1WYddYnKiBxA07fgXo8-Dk8bvJUgTyxk=w300-h407-no?authuser=0)![enter image description here](https://lh3.googleusercontent.com/Y5ZCGrGB_RGQNZHPy8cMsEM4ysrbRERoPqa56Fhh-AiADuZy8hTqIxp31B4djHtWmIFu-o9NcVEdI1KAxZJUY0HUlcb-yEqM9sN91mjgRR1BgkxQIPTjjhUJpHyNffSfpAi0HS7vL3ZvGsqr1m6jXO4S1HGjZYECDZe4mLF5uLfV5oWtkhvVR-7KunugD70LW1sKrPRY0iZTUVg64-a6jpxzAOgkTAStI8JbJ7iO4nkxXNp20B8aO40Aa2KWLx5i_vDnpo6NwxvuhKh8Gzc-5IaNE5n4n60wjlTpDnzJOSVsosw36awIOTObVRN9LUxpLu5acdyTfo-rQiUarOwEmW80M_gu5DqwYlOhP1bjQI3oaDbOqJ9kChCcI9Awn6SMxjWiu_uvuguUpTmjErfGLCRQLlQ9vvq7FRSjd6rIsliAY4pDQ8SGKJhqhb1S8CbzZwUQk2DyouhBetB-uo5TUR_MbLLiuXfiuBUXlEsj2Miub_S0zGLnkz75XQps_NXV2WHiIdx3bi0_Vgnzg0aeJzZZLUT5jVEREExWvzbPv2cc-Jtzm-zazsXVWlwCSfSujuz3_ZFv16ydgJlTHLwD7GO8Zl_hlmP6SqS1UbM9ySY1xt6pBG9hrhOkJwQNfruaqxTpTZd4z8tHkj7amFIQTyZ7yrAAOE5HS8nKRIhryfq-n3uFggw51k9rkDYplnQfIaCvYm2JYFg1C6yg8xZ9biI=w300-h409-no?authuser=0)![enter image description here](https://lh3.googleusercontent.com/db4qf_dheqfvao5VsP6TusfhDhrQXHzFLT5BKM1O6E3ZAZsRn8SLOXXcoTcOl1_QnKsNPaDPo3hXgBhpJJkvZ2LbGRcgI__ZF63Q7DfTOTTsDactVy0vWrQR4o8tkNsCh5lReqUoMDu4U6c_J6e533OnDSBQJJc42dVJjK6aE2AMlpjBy2OtWkpAQndPjXzJggnHl9162gbrxliHZMDV0MYBTwxfw1XbsF8mQSCr2E0pfbtouRGlXTdSrd1sNPnMe9Ff0C7CnCpjYiH1_EiSSyWH79iBAAgPKcPeaP7V6BNt0_qJRDjEHJjiVLddThWkQfodfA2LyiogefP3iH_QcV5GcEqaeTLmXA5Cu2DgblItBBimIvlXxem5eGu-bcAszua8v3GYfO-6stn5fxF7mSj08z4arwjECf-rHrjhAIMFlFcQVdUDcolWUm9EDHBE7yUEyGTDt-StQVBWHq6njnxghX39aa-m1Ja1eDo5sNmqJiR2b4Um-iexcRT8VGRnR04R9VcyHTG8s6bwsJ2XpoqZPeWaJOSmUHl9IphQZE1jMHGAdA_Yym9mtlFvlkfBsoVQci-vvJ_rweR4R8WRXLA45mLo4UiU6POE-XWFKCUcxmd9mY39fJBbW4dZytqaWmdpYoBBDkYjfCRp29XVeAtZyTHqvFtRfYewwFfTlhT8y8IHLnY1lFq-SnERJit8uMgdu1YwH8syRpFlmVOfUdw=w300-h406-no?authuser=0)![enter image description here](https://lh3.googleusercontent.com/F9tyI32MvGRt1lhRIUJdEwF59iQI1YrWMDFUUDbTNKA3udEXYpPoD3E6iv8v6MmUOFg-yXc4PidELs7xNiLbMMT7QlRxHKadk-mTKroq4ZE6M9T1tFpB4gKZN-7KTn0nL4MHraLBt8HfvDC7OPxJUWHlEYV9b-2-cvWXZinFkhSu8pf9d8PcNh1kXnamNywwdmFHsKI9Fk1jV4Zg5rqeXlSi9nMl5hZfB5MNcY5YC-Tf4YsATjPYkhPVlbS-pUFz5BNUpdqsuC3MX64EmOPavHdBo7rppILD0hKlCpD8-65c0eGyxJXQHXFXIP2MjIvKv5HbpcGfq2tTM8YSytoLCh9GlvMuQw-11t0ctkqpwimNz44q5-JpszOOW-srAU_iz0AexT8_T7slB-2AmOVZuoFFjAyc5Qn8pj6l8xT8lNiDVp70NLcYoc3eRc-JIc4e4IoYmnheMJYecP_tZsLmCnlFOOdP9my3C1i_PgCQO0dIg9VW_ku6vGm3AE-qCgjCYOwEtu55d50VVbtfL8HD9saS_tjKV02KMZDxRyYjvVoDrwMQpkPRo5V-ZiAtP272w1jiX1QCw0ZNhJ53_wFmiixwTTZ26gZw6cO5HCGaNWPfKSEQBNB2C5kM56NAP3Sg-_GAYWN2JCNMpSAIqPb5ikyz3vA2HtDfUmwnDA1XAkA94Iuj_gGh8r0dol7WEY8Beq-q5TkVtP9v6S-HFyfmWQo=w300-h407-no?authuser=0)![enter image description here](https://lh3.googleusercontent.com/CGv7fZLTnQcpkCepDM1Ni6dnVB-bCx5QanxS76kNmMwc8F74jvxwNEH0AE0SZ1C0pjy-v0VNBEMhSUT8w1-9lO6JaZZMWxm_TN7ZBL_0X7hq0oG7aWwKXixbNTN8v0oAjLNANXQDHqW0MljDen70DVBycmLkibmN0HOJCCF-7luOq6mZUc-yzvhl54TsAd_rWm6yywm4VtAAJjCgyU3Ndh6aqsX-Ja4gqf0lZq2qeqbAa7c6z8CjlZTBUXzfpXFjSXYUEgVEr_7ZijtwZzQ0iJyx-1mLzjWU4mlCrn9iy0oDhHYtj0CdRwNVuIjbnggSYU4qlaVGaKuIvZhhuJKHj_bpHB20Uqex-3Es1b1rIPrs2_4SDMj1R_H-7Mnu26eeqmeRTCDo7FP8N_JvnQBoOwvAPslrVgt9tzDdrTg5t2ry4rwGCkGkD4ASBuyjJqoWuzu_lvHKyW1M91i4LrfV6Q5oBwuqMW2tF0j-WEJ1hoc7Aiy7GsjVoOWqA7RVSa4IafMDs_h4vcXSjr8cRp9K6VWvs4jpbjyG0kIvW9NzyRJDooyCg0fOPD0KNydG0NuMHOI4mRGlC0loqIuSqkqOMXh6dp4nJX2xwxU0DfgsMLnKpVlyjRZ7FjOrkUntLs860sSTPVNZhzkyTrFHx3l_GXhGVzIkRkVUM557K7A_msMPODeypoOMqmZN-pNz9VV60KpsOTRBwvDd-tv52z9lLKU=w300-h406-no?authuser=0)

# Procedure Of Making This Game

 1. IntelliJ & Scala
 2. Version Control Systems - Git
 3. ScalaTest
 4. Text-User-Interface (TUI)
 5. MVC Architecture
 6. Travis & Coveralls
 7. Design Patterns
 8. Scala Swing (GUI)
 9. Interfaces & Components
 10. Dependency Injection
 11. File IO (XML & JSON)
 12. Docker 
 13. Documentation


# Used Design Patterns
* Builder-Pattern *[PlayerBuilder](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/model/playerComponent/PlayerBuilder.scala)*
* State-Pattern [*TUI State*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/aview/TUIState.scala)  [*Player State*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/controller/controllerComponent/PlayerState.scala)
* Try-Monade [*replaceCell() (124)*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/controller/controllerComponent/PlayerState.scala)
* Option-Monade [*Dice*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/model/gameboardComponent/gameboardBaseImpl/Dice.scala)
* Strategy-Pattern [*Block-Strategy*](https://github.com/franzgajewski/malefiz/blob/master/src/main/scala/de/htwg/se/malefiz/util/BlockStrategy.scala)
# Coverage [Master]
[![Build Status](https://travis-ci.org/franzgajewski/malefiz.svg?branch=development&kill_cache=1)](https://travis-ci.org/franzgajewski/malefiz) [![Coverage Status](https://coveralls.io/repos/github/franzgajewski/malefiz/badge.svg?branch=master&kill_cache=1)](https://coveralls.io/github/franzgajewski/malefiz?branch=master&kill_cache=1)

Written by [@sehirsig](https://github.com/sehirsig/) & [@franzgajewski](https://github.com/franzgajewski/)
