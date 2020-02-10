<#import "parts/page.ftlh" as p>

<@p.page>
    <div>
        <h1>Результат</h1>
        <table>
            <tr>
                <th>${from}</th>
                <th>${to}</th>
            </tr>
            <tr>
                <td>${fromAmount}</td>
                <td>${convertRes}</td>
            </tr>
        </table>

        <a href="/converter">Конвертер</a><br>
        <a href="/history">История транзакций</a>
    </div>
</@p.page>