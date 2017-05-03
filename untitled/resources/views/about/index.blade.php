<!doctype html>
<html lang="ja">
 <head> 
  <meta charset="UTF-8"> 
  <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
  <title>初心者向けホームページテンプレート tp_beginner2</title> 
  <meta name="viewport" content="width=device-width, initial-scale=1"> 
  <meta name="description" content="ここにサイト説明を入れます"> 
  <meta name="keywords" content="キーワード１,キーワード２,キーワード３,キーワード４,キーワード５"> 
  <link rel="stylesheet" href="../css/style.css"> 
  <script type="text/javascript" src="../js/openclose.js"></script> 
  <!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]--> 
 </head> 
 <body> 
  <div id="container"> 
   <header> 
    <h1 id="logo"><a href="/"><img alt="Simple Site" src="../image/logo.png"></a></h1> 
   </header> 
   <nav id="menubar"> 
    <ul> 
     <li><a href="/">Home</a></li> 
     <li><a href="/about/">About</a></li> 
     <li><a href="/gallery/">Gallery</a></li> 
    </ul> 
   </nav> 
   <section> 
    <h2>About</h2> 
    <p>このサイトの説明ページです。</p> 
    <h2>tableサンプル</h2> 
    <table class="ta1"> 
     <tbody>
      <tr> 
       <th colspan="2" class="tamidashi">見出しが必要な場合はここに入れます</th> 
      </tr> 
      <tr> 
       <th>見出し</th> 
       <td>ここに説明など入れて下さい。サンプルテキスト。</td> 
      </tr> 
      <tr> 
       <th>見出し</th> 
       <td>ここに説明など入れて下さい。サンプルテキスト。</td> 
      </tr> 
      <tr> 
       <th>見出し</th> 
       <td>ここに説明など入れて下さい。サンプルテキスト。</td> 
      </tr> 
      <tr> 
       <th>見出し</th> 
       <td>ここに説明など入れて下さい。サンプルテキスト。</td> 
      </tr> 
      <tr> 
       <th>見出し</th> 
       <td>ここに説明など入れて下さい。サンプルテキスト。</td> 
      </tr> 
      <tr> 
       <th>見出し</th> 
       <td>ここに説明など入れて下さい。サンプルテキスト。</td> 
      </tr> 
     </tbody>
    </table> 
   </section> 
   <section id="about"> 
    <h2>当テンプレートについて</h2> 
    <h3>当テンプレートはhtml5+CSS3(レスポンシブWEBデザイン)です</h3> 
    <p>当テンプレートは、パソコン、スマホ、タブレットでhtml共通のレスポンシブWEBデザインになっております。<br> 古いブラウザ（※特にIE8以下）で閲覧した場合にCSSの一部が適用されない（角を丸くする設定など）のでご注意下さい。</p> 
    <h3>各デバイスごとのレイアウトチェックは</h3> 
    <p>最終的なチェックは実際のタブレットやスマホで行うのがおすすめですが、臨時チェックは最新のブラウザ(IEならIE10以降)で行う事もできます。ブラウザの幅を狭くしていくと、各端末サイズに合わせたレイアウトになります。</p> 
    <p>注意：cssはリアルタイムで反映されますが、javascript(js)は<span class="color1">ブラウザを再読み込み</span>させないと反映されないので、レイアウトが切り替わったらブラウザを再読み込みさせる事をおすすめします。javascriptは小さい端末用の開閉ブロックなどに使われています。</p> 
    <h3>各デバイス用のスタイル変更は</h3> 
    <p>cssフォルダのstyle.cssファイルで行って下さい。詳しい説明も入っています。<br> 前半はパソコン環境を含めた全端末の共通設定になります。中盤以降、各端末向けのスタイルが追加設定されています。<br> media=" (～)"の「～」部分でcssを切り替えるディスプレイのサイズを設定しています。ここは必要に応じて変更も可能です。</p> 
   </section> 
   <section> 
    <h2>当テンプレートの使い方</h2> 
    <h3>titleタグ、copyright、metaタグ、他の設定</h3> 
    <p><strong class="color1">■titleタグの設定はとても重要です。念入りにワードを選んで適切に入力しましょう。</strong><br> まず、htmlソースが見れる状態にして、上から６行目あたりにある、<br> <span class="look">&lt;title&gt;初心者向けホームページテンプレート tp_beginner2&lt;/title&gt;</span><br> を編集しましょう。<br> あなたのホームページ名が「SIMPLE SITE」だとすれば、<br> <span class="look">&lt;title&gt;SIMPLE SITE&lt;/title&gt;</span><br> とすればＯＫです。</p> 
    <p><strong class="color1">■copyrightを変更しましょう。</strong><br> 続いてhtmlの下の方にある、<br> <span class="look">Copyright© SIMPLE SITE All Rights Reserved.</span><br> の「SIMPLE SITE」部分もあなたのサイト名に変更します。</p> 
    <p><strong class="color1">■metaタグを変更しましょう。</strong><br> htmlソースが見える状態にしてmetaタグを変更しましょう。</p> 
    <p>ソースの上の方に、<br> <span class="look">content="ここにサイト説明を入れます"</span><br> という部分がありますので、テキストをサイトの説明文に入れ替えます。検索結果の文面に使われる場合もありますので、見た人が来訪したくなるような説明文を簡潔に書きましょう。</p> 
    <p>続いて、その下の行の<br> <span class="look">content="キーワード１,キーワード２,～～～"</span><br> も設定します。ここはサイトに関係のあるキーワードを入れる箇所です。10個前後ぐらいあれば充分です。キーワード間はカンマ「,」で区切ります。</p> 
    <p><strong class="color1">■ロゴ画像のalt指定と、ロゴ画像本体も変更しましょう。</strong><br> html側に<br> <span class="look">&lt;img src="images/logo.png" alt="Simple Site"&gt;</span><br> となっている箇所があるので、ここのalt指定(Simple Site)もあなたのサイト名に変更しましょう。</p> 
    <p>ロゴ画像本体については、baseフォルダに文字なしの土台画像「logo.png」が入っているので、画像ソフトなど使ってあなたのサイト名を入れて、imagesフォルダに上書きしましょう。このロゴ画像、HPで見るサイズよりかなり大きく感じると思いますが、高解像度の端末でピンボケさせない為に適当に大きくしてあります。</p> 
    <p>レイアウト上のロゴの大きさは、cssフォルダのstyle.cssの<br> 
     <spna class="color1">
      /*ヘッダー（ロゴが入った最上段のブロック）
     </spna><br> でブロックで設定されている、<br> <span class="color1">#logo img</span><br> にあるwidthの値で変更可能です。</p> 
    <h3>HPの外側の背景画像は（※「tp_beginner2_1」の例）</h3> 
    <p>cssフォルダのstyle.cssの一番上にある、<br> <span class="color1">body {</span><br> のブロックで設定されている<br> <span class="color1"> background: #fff url(../images/bg.jpg) no-repeat center center/cover fixed; /*背景指定*/</span><br> の「../images/bg.jpg」で変更できます。</p> 
    <p> 「../」というのは「１つ上の階層」を意味します。つまり、cssフォルダのstyle.cssから見て１つ上の階層にあるimagesフォルダにあるbg.jpgを読み込む、という意味です。<br> 例えば、bg.jpgもstyle.cssも同じ階層（同じフォルダ内）にあるなら、「url(bg.jpg)」だけでいいということになります。</p> 
    <p>「no-repeat center center」は、no-repeat(画像をリピート表示させない指定)、center(左右の中央)、center(上下の中央)の意味です。<br> 「cover fixed」は、cover(画像が画面全体に表示される指定)、fixed(スクロールしても固定表示させる指定)です。fixedはiPhoneやiPadや古い端末などでは使えず通常スクロールになります。</p> 
    <p>書き忘れましたが「#fff」は背景色（この場合は白）です。画像を使わない場合はこれだけ（background: #fff;）でOKです。</p> 
    <p>その上の行で指定している<br> background: #fff url(../images/bg.jpg) no-repeat center center fixed; /*背景指定（古いブラウザ用）*/<br> は、コメントにあるように古いブラウザ用（主にIE8以下）です。coverに対応しておらず、画像は原寸大表示になります。</p> 
    <h3>このHP枠は</h3> 
    <p>cssフォルダのstyle.cssの<br> <span class="color1">#contents {</span><br> のブロックで変更できます。</p> 
    <h3>ここの見出しの色は</h3> 
    <p>cssフォルダのstyle.cssの<br> <span class="color1">#contents h2 {</span><br> のブロックで変更できます。</p> 
    <h3>その他、テンプレートのデザイン変更などは</h3> 
    <p>cssフォルダのstyle.cssで行って下さい。詳しい解説も書かれています。<br> cssの解説は、「<span class="color1">/*</span>」と「<span class="color1">*/</span>」の間にコメントとして入れています。「<span class="color1">/*</span>」と「<span class="color1">*/</span>」はcss用のコメントタグであり、飾りではないので削除をしないで下さい。もし解説を削除したい場合は、「<span class="color1">/*</span>」と「<span class="color1">*/</span>」含めて丸ごと削除して下さい。</p> 
    <h3>プレビューでチェックすると警告メッセージが出る場合(一部ブラウザ対象)</h3> 
    <p>主にjavascript（jsファイル）ファイルによって出る警告ですが、WEB上では出ません。また、この警告が出ている間は効果を見る事ができないので、警告メッセージ内でクリックして解除してあげて下さい。これにより効果がちゃんと見れるようになります。</p> 
    <h3>うまく編集できない場合は</h3> 
    <p><a href="http://template-party.com/bbs/">サポート掲示板</a>からご質問下さい。対応可能な範囲内でサポートしております。</p> 
   </section> 
  </div> 
  <!--/container--> 
  <footer> 
   <small>Copyright© <a href="/">SIMPLE SITE</a> All Rights Reserved.</small> 
   <span class="pr">《<a href="http://template-party.com/" target="_blank">Web Design:Template-Party</a>》</span> 
  </footer> 
  <!--スマホ用更新情報　480px以下--> 
  <script type="text/javascript" src="../">
if (OCwindowWidth() <= 480) {
	open_close("newinfo_hdr", "newinfo");
}
</script>   
 </body>
</html>