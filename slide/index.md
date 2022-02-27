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

Scalebaseの紹介スライド

---

## Eff（Extensible Effects）とは
- interpreter
  - テスト用のinterpreter差し替え

---

ユースケースでスマートコンストラクタ使う例

--- 

ユースケースにスタック渡してrunする例

---

- ドメイン層（ドメインロジック・抽象Repository）にも採用
- Eff[R, A]型を引き回すことが多い

---

コーディング集

---

map, flatMap

---

pureEff[R, A]

---

traverse, sequence

---

validated -> runNel

---

runPure

---

コーディングレベルでの悩みポイント
- どのタイミングでEff[R, A]に変換するか
- flatMapのコンテキスト指定


---

メリット
- ドメインに集中できる
  - 日々の業務で技術的関心事を意識することが減る
  - シグネチャに現れるエフェクトによって可読性が増す
- 学習コストが低い?（基盤が整っている前提）
  - モナトラで組み合わせる方が難しい

---

アルプ独自の書き方

---

スマートコンストラクタ

---