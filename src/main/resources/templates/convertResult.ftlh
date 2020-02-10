<#import "parts/page.ftlh" as p>

<@p.page>
    <link rel="stylesheet" type="text/css" href="css/ConvertResStyle.css">
    <div class="container">
        <h1>Результат</h1>
        <article class="line">${from}</article>
        <article class="line">${to}</article>

        <article class="line">${fromAmount}</article>
        <article class="line">${convertRes}</article>
        <a href="/converter">Конвертер</a>
        <a href="/history">История транзакций</a>
    </div>
</@p.page>