<#import "parts/page.ftlh" as p>
<#import "parts/login.ftlh" as l>

<@p.page>
<div>
    <h1>Конвертер</h1>
    <form action="/history/save" method="post">
        <label>
            <select name="fromCharcode">
                <#list curCodes2Names as curCode2Name>
                    <option value="${curCode2Name['charcode']}">(${curCode2Name['charcode']}) ${curCode2Name['name']}</option>
                </#list>
            </select>
        </label>
        <label>
            <select name="toCharcode">
                <#list curCodes2Names as curCode2Name>
                    <option value="${curCode2Name['charcode']}">(${curCode2Name['charcode']}) ${curCode2Name['name']}</option>
                </#list>
            </select>
        </label>
        <br><input type="number" min="0" name="from">
        <button type="submit">Конвертировать</button>
    </form>
    <@l.logout/>
    </@p.page>
</div>