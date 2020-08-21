---
marp: true
---
<!--
headingDivider: 1
-->

# OptionとEitherによるログイン処理のエラーハンドリングを検討

# 自己紹介
## 名前
- 久代太一（久代太一）

## 会社
- 株式会社ネクストビートの20新卒エンジニア
- キズナコネクトという保育園の業務支援Saas
- 労務管理機能があるので、労基法周りのドメインロジック多め

# 自己紹介
## 技術
- 学生時代
  - 研究でPythonを用いた数理最適化と機械学習（領域は物流。通称、オペレーションズリサーチ）
  - 個人でGo, React
- 社会人
  - 業務はScala(Play Framework), TypeScript(Angular, RxJS)
  - 個人でHaskell, オブジェクト指向, DDD, 圏論, アジャイル, Rust, インタプリンタ...

# 自己紹介
## Scala歴
- 去年の12月から本格的に勉強開始で半年強
- プロダクト開発としてScalaを用いている身
- とにかくScalaが好き

<!--
# 今日のテーマ
## Scala初学者がつまずきがちなところは？
- Option, Either, Future, Try
  - 値の操作方法を覚える
- for yield式
  - map, flatMapとの対応
- match式
  - switch式との違いは？
- apply, unapply
  - DBやフォームとのマッピング?
- case class, object, trait, companion object
  - どのように使い分ける?
-->

# 今日のテーマ
### ログイン処理のエラーハンドリングにおけるOptionとEitherの使用法を検討する

# 対象別の発表の目的
## 全くのScala初学者
- 「こういう便利な機能があるんだな〜」

## Scalaの文法を学んだが開発の経験がない方（一番参考になる？）
- Scalaの機能が実際にどう開発に用いられているかを知る

## 開発経験が豊富な方
- 自分が初学者に説明するのであればこうやる等の改善点があれば

# ログイン処理のエラーハンドリング

# テーブル定義
- user
  - id
  - name
- user_password
  - user_id (idと対応)
  - password

# 処理の流れを整理
1. ユーザーから`name`と`password`の入力を受け取る
2. 入力で受け取った`name`によってDBから`user`インスタンスを取得
3. `user`が取得できたかどうかのエラーハンドリング
4. `user.id`によって`userPassword`インスタンスを取得
5. `userPassword.password`と入力で受け取った`password`を比較し、`password`が正しいかのエラーハンドリング
6. 全て正常であれば認証処理を行う

# DBから値を取得するメソッド
```scala
// Userクラスインスタンスを取得
def getByName(name: String): Future[Option[User]] = ???

// UserPasswordクラスインスタンスを取得
def get(userId: User.Id): Future[Option[Userpassword]] = ???
```

# Optionで実装してみる

# Optionの概要
画像を貼り付け

# コントローラー処理（Option ver）
```scala
(login: LoginFormData) => {
  for {
    userOpt: Option[User] <- userDao.getByName(login.name)
    result:  Result       <- userOpt match {
      case None       => Future.successful(NotFound("not found name"))
      case Some(user) =>
        for {
          Some(userPassword): Option[UserPassword] <- userPasswordDao.get(user.withId)
          result:             Result               <- userPassword.verify(login.password) match {
            case false => Future.successful(Unauthorized("invalid password"))
            case true  => authMethods.loginSuccess(user, Redirect(homeUrl))
          }
        } yield result
    }
  } yield result
})
```

# Optionで書いた場合
- ネストが深くて読みづらい
- ネストが深すぎるとエラーハンドリング処理が書きにくいことも
- `None`はあくまで**値がない**という情報しか持たない

# Eitherで実装してみる

# Eitherの概要
画像を貼り付け

# コントローラー処理（Either ver）
```scala
土日で作業
```

# Eitherで書いた場合
- ネストが浅くなり読みやすくなった
- `Left`により`どんなエラーが起きたか`という情報を持つ

# EitherTで書いた場合（番外編）
```scala
土日で作業
```

# まとめ

# Scalaを学ぶにあたってやったこと
- I: ドワンゴ研修資料
- I: Tour of Scala
- O: Todoアプリ作成
- I: N予備校基礎・応用
- I: 実践Scala入門
- I: 入社した会社の研修資料（標準ライブラリのメソッドを学ぶ）
- I: Scalaの標準ライブラリを読む
- O: Todoアプリ作成（２回目）

# アウトプットするときにサンプルコードが欲しい

# 初学者向けのベースプロジェクトを作りました

### [taichi0315/scala-play-auth-sample](https://github.com/taichi0315/scala-play-auth-sample)

# ベースプロジェクト概要
- Play FrameWorkを使用
- 簡易的なユーザー認証処理ができる状態
- あえて標準ライブラリのみで実装
- ブランチを切り替えると色々な実装パターンが（処理内容は同じ）
- イシューに改善点を列挙してある（コメント・イシュー追加歓迎します！）

# 楽しいScalaライフを！