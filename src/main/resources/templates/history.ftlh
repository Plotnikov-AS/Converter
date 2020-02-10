<#import "parts/page.ftlh" as p>

<@p.page>
    <div>
        <form action="/search" method="post">
            <input type="date" name="dateStr" placeholder="Дата">
            <select name="fromCharcode">
                <#list curCodes2Names as curCode2Name>
                    <option value="${curCode2Name['charcode']}">(${curCode2Name['charcode']}
                        ) ${curCode2Name['name']}</option>
                </#list>
            </select>
            <select name="toCharcode">
                <#list curCodes2Names as curCode2Name>
                    <option value="${curCode2Name['charcode']}">(${curCode2Name['charcode']}
                        ) ${curCode2Name['name']}</option>
                </#list>
            </select>
            <button type="submit">Найти</button>
        </form>
        <#if (histories?size > 0)>
        <table>
            <tr>
                <th>Исходная валюта</th>
                <th>Целевая валюта</th>
                <th>Исходная сумма</th>
                <th>Получаемая сумма</th>
                <th>Курс на дату (1 к 1)</th>
                <th>Дата</th>
            </tr>
            <#foreach history in histories>
                <tr>
                    <td>${history.fromCurrency}</td>
                    <td>${history.toCurrency}</td>
                    <td>${history.fromAmount}</td>
                    <td>${history.toAmount}</td>
                    <td>${history.courseOnDate}</td>
                    <td>${history.date?date}</td>
                </tr>
            </#foreach>
            <#else>
                Транзакций не найдено
            </#if>
        </table>
        <a href="/converter">Конвертер</a>

    </div>

</@p.page>