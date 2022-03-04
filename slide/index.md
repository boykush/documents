---
marp: true
theme: scalebase
paginate: true
footer: '![](https://scalebase.com/assets/img/common/header_logo.svg?20220208)'

---

# Eff（atnos-eff）による実践的なコーディング集

Kushiro Taichi at Alp,Inc.

---

## 自己紹介

- Kushiro Taichi ( shiroichi315 )
- アルプ株式会社 2021.06 ~
- キーワード
  - Scala
  - DDD
  - Agile
  - FP
  - Tortoiseshell

![bg right:40% cover](icon.jpeg)

---

## Eff（Extensible Effects）とは
- 「Freer Monads, More Extensible Effects」で紹介
- 実装面の特徴
  - 複数のエフェクトをフラットに扱う
  - Interpreterによる実行の分離

---

## 複数のエフェクトをフラットに扱う
- for式に含まれるエフェクトを型パラメータで受け取る
- スマートコンストラクタによって `Eff[R, A]`型に変換

![image](4_page.png)

--- 

## Interpreterによる実行の分離
- Open Unionによりエフェクトのスタックを定義
- Interpreterによる実行

![image width:1150px](5_page.png)

---

## 実務のコードでは...？
- 例として`Factory`や`Repository`にエフェクトを用いることも
- `Eff[R, A]`型を複数のメソッドで引き回すことが多い

![image](6_page.png)

---

# コーディング集

実務に近いコードを紹介していきます

---

## map
- `A`型の値に関数を適用

![image width:1150px](8_page.png)

---

## pureEff
- pureな `A` 型の値を `Eff[R, A]`型に変換

![image](9_page.png)

---

## traverseA
- `Eff[R, A]`型を返す処理を走査する
- `sequenceA`も同様に存在

![image](10_page.png)

---

runPure

---

# コーディングレベルでの悩みポイント

---


## どのタイミングでEff[R, A]に変換するか
- テストが煩雑になることも
- ドメインロジックはピュアに書く？

![image](14_page.png)

---

## option, list エフェクトを用いるか
- `runXxx`によって実行順を制御
- 処理に対する実行順の意識が必要になり基本的には使っていない

---

- flatMapのコンテキスト指定
- option, list effect使う？


---

メリット
- ドメインに集中できる
  - 日々の業務で技術的関心事を意識することが減る
  - シグネチャに現れるエフェクトによって可読性が増す
- 学習コストが低い?（基盤が整っている前提）
  - モナトラで組み合わせる方が難しい
  - API見に行く回数は少ない

---

アルプ独自の書き方

---

# To Be Continued...

![bg cover 40% opacity:0.4](alp_effect.png)

---